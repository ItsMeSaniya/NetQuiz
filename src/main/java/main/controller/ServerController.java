package main.controller;

import main.model.Message;
import main.model.User;
import main.network.MessageHandler;
import main.network.Server;
import main.network.ServerStatusListener;
import main.util.NetworkConstants;

/**
 * Controller for server operations
 * Follows Single Responsibility Principle
 */
public class ServerController {
    private Server server;
    private final User currentUser;
    private final ServerStatusListener statusListener;
    private boolean running;
    
    public ServerController(User currentUser, ServerStatusListener statusListener) {
        this.currentUser = currentUser;
        this.statusListener = statusListener;
        this.running = false;
    }
    
    /**
     * Start the server on specified port
     * @param port The port to start on
     * @param messageHandler The message handler for incoming messages
     * @return true if started successfully
     */
    public boolean startServer(int port, MessageHandler messageHandler) {
        if (running) {
            statusListener.onServerStatus("❌ Server is already running!");
            return false;
        }
        
        if (port < NetworkConstants.MIN_PORT || port > NetworkConstants.MAX_PORT) {
            statusListener.onServerStatus("❌ Invalid port! Must be between " + 
                NetworkConstants.MIN_PORT + "-" + NetworkConstants.MAX_PORT);
            return false;
        }
        
        try {
            server = new Server(port, messageHandler, currentUser.getUsername());
            server.start();
            running = true;
            statusListener.onServerStatus("✅ Server started on port " + port);
            return true;
        } catch (Exception e) {
            statusListener.onServerStatus("❌ Failed to start: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Stop the server
     */
    public void stopServer() {
        if (!running || server == null) {
            statusListener.onServerStatus("❌ Server is not running!");
            return;
        }
        
        server.stop();
        running = false;
        statusListener.onServerStatus("✅ Server stopped");
    }
    
    /**
     * Broadcast a message to all connected clients
     * @param message The message to broadcast
     */
    public void broadcast(Message message) {
        if (running && server != null) {
            server.broadcast(message);
        }
    }
    
    /**
     * Check if server is running
     * @return true if running
     */
    public boolean isRunning() {
        return running;
    }
    
    /**
     * Get the server instance
     * @return The server or null if not running
     */
    public Server getServer() {
        return server;
    }
}
