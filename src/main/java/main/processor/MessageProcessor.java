package main.processor;

import main.model.Message;
import main.network.PeerConnection;

/**
 * Base interface for processing different message types
 * Follows Open/Closed Principle - open for extension, closed for modification
 */
public interface MessageProcessor {
    /**
     * Process a message
     * @param message The message to process
     * @param connection The connection the message came from
     */
    void process(Message message, PeerConnection connection);
    
    /**
     * Get the message type this processor handles
     * @return The message type
     */
    Message.MessageType getHandledType();
}
