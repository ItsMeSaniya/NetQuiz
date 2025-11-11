package main.controller;

import main.model.Message;
import main.model.User;
import main.network.Client;
import main.network.PeerConnection;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Controller for chat operations
 * Follows Single Responsibility Principle
 */
public class ChatController {
    private final User currentUser;
    private final List<Client> connectedPeers;
    private final Map<String, PeerConnection> peerConnections;
    private final Consumer<String> chatLogger;
    private final Consumer<String> broadcastLogger;
    private final Consumer<String> p2pLogger;
    
    public ChatController(User currentUser, 
                         List<Client> connectedPeers,
                         Map<String, PeerConnection> peerConnections,
                         Consumer<String> chatLogger,
                         Consumer<String> broadcastLogger,
                         Consumer<String> p2pLogger) {
        this.currentUser = currentUser;
        this.connectedPeers = connectedPeers;
        this.peerConnections = peerConnections;
        this.chatLogger = chatLogger;
        this.broadcastLogger = broadcastLogger;
        this.p2pLogger = p2pLogger;
    }
    
    /**
     * Send a group chat message
     * @param content The message content
     */
    public void sendGroupMessage(String content) {
        if (content == null || content.trim().isEmpty()) {
            return;
        }
        
        Message message = new Message(
            currentUser.getUsername(),
            "all",
            content,
            Message.MessageType.TEXT
        );
        
        if (!connectedPeers.isEmpty()) {
            connectedPeers.get(0).sendMessage(message);
            chatLogger.accept("You: " + content);
        } else {
            chatLogger.accept("[ERROR] Not connected to server!");
        }
    }
    
    /**
     * Send a broadcast message
     * @param content The message content
     */
    public void sendBroadcast(String content) {
        if (content == null || content.trim().isEmpty()) {
            return;
        }
        
        Message message = new Message(
            currentUser.getUsername(),
            "all",
            content,
            Message.MessageType.BROADCAST
        );
        
        if (!connectedPeers.isEmpty()) {
            connectedPeers.get(0).sendMessage(message);
            broadcastLogger.accept("[BROADCAST] You: " + content);
        } else {
            broadcastLogger.accept("[ERROR] Not connected to server!");
        }
    }
    
    /**
     * Send a peer-to-peer message
     * @param recipient The recipient username
     * @param content The message content
     */
    public void sendP2PMessage(String recipient, String content) {
        if (content == null || content.trim().isEmpty() || recipient == null) {
            return;
        }
        
        Message message = new Message(
            currentUser.getUsername(),
            recipient,
            content,
            Message.MessageType.PEER_TO_PEER
        );
        
        boolean isAdmin = isUserAdmin();
        
        if (isAdmin) {
            // Admin sends directly to peer
            PeerConnection targetConnection = peerConnections.get(recipient);
            if (targetConnection != null) {
                targetConnection.sendMessage(message);
                p2pLogger.accept("You → " + recipient + ": " + content);
            } else {
                p2pLogger.accept("[ERROR] Peer not found: " + recipient);
            }
        } else {
            // Client sends through server
            if (!connectedPeers.isEmpty()) {
                connectedPeers.get(0).sendMessage(message);
                p2pLogger.accept("You → " + recipient + ": " + content);
            } else {
                p2pLogger.accept("[ERROR] Not connected to server!");
            }
        }
    }
    
    /**
     * Check if current user is admin
     * @return true if admin
     */
    private boolean isUserAdmin() {
        return currentUser.getUsername().equalsIgnoreCase("admin") && 
               currentUser.getPassword().equals("admin");
    }
}
