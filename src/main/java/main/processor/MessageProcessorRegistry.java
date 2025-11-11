package main.processor;

import main.model.Message;
import main.network.PeerConnection;

import java.util.HashMap;
import java.util.Map;

/**
 * Registry for message processors
 * Uses Strategy Pattern to delegate message processing
 * Follows Open/Closed Principle
 */
public class MessageProcessorRegistry {
    private final Map<Message.MessageType, MessageProcessor> processors;
    
    public MessageProcessorRegistry() {
        this.processors = new HashMap<>();
    }
    
    /**
     * Register a processor for a specific message type
     * @param processor The processor to register
     */
    public void register(MessageProcessor processor) {
        processors.put(processor.getHandledType(), processor);
    }
    
    /**
     * Process a message using the appropriate processor
     * @param message The message to process
     * @param connection The connection the message came from
     */
    public void process(Message message, PeerConnection connection) {
        MessageProcessor processor = processors.get(message.getType());
        if (processor != null) {
            processor.process(message, connection);
        } else {
            System.err.println("No processor found for message type: " + message.getType());
        }
    }
    
    /**
     * Check if a processor exists for a message type
     * @param type The message type
     * @return true if processor exists
     */
    public boolean hasProcessor(Message.MessageType type) {
        return processors.containsKey(type);
    }
    
    /**
     * Remove a processor
     * @param type The message type
     */
    public void unregister(Message.MessageType type) {
        processors.remove(type);
    }
}
