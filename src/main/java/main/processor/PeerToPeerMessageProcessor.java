package main.processor;

import main.model.Message;
import main.network.PeerConnection;

import java.util.function.Consumer;

/**
 * Processor for PEER_TO_PEER messages
 * Follows Open/Closed Principle
 */
public class PeerToPeerMessageProcessor implements MessageProcessor {
    private final Consumer<String> p2pLogger;
    
    public PeerToPeerMessageProcessor(Consumer<String> p2pLogger) {
        this.p2pLogger = p2pLogger;
    }
    
    @Override
    public void process(Message message, PeerConnection connection) {
        String displayMessage = message.getSender() + " → You: " + message.getContent();
        p2pLogger.accept(displayMessage);
    }
    
    @Override
    public Message.MessageType getHandledType() {
        return Message.MessageType.PEER_TO_PEER;
    }
}
