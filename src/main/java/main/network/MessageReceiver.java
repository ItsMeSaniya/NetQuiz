package main.network;

import main.model.Message;

/**
 * Interface for receiving messages
 * Follows Interface Segregation Principle
 */
public interface MessageReceiver {
    /**
     * Called when a message is received
     * @param message The received message
     * @param connection The connection that received the message
     */
    void onMessageReceived(Message message, PeerConnection connection);
}
