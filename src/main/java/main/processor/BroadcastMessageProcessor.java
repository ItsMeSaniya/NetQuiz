package main.processor;

import main.model.Message;
import main.network.PeerConnection;

import java.util.function.Consumer;

/**
 * Processor for BROADCAST messages
 * Follows Open/Closed Principle
 */
public class BroadcastMessageProcessor implements MessageProcessor {
    private final Consumer<String> broadcastLogger;
    
    public BroadcastMessageProcessor(Consumer<String> broadcastLogger) {
        this.broadcastLogger = broadcastLogger;
    }
    
    @Override
    public void process(Message message, PeerConnection connection) {
        String displayMessage = "[BROADCAST] " + message.getSender() + ": " + message.getContent();
        broadcastLogger.accept(displayMessage);
    }
    
    @Override
    public Message.MessageType getHandledType() {
        return Message.MessageType.BROADCAST;
    }
}
