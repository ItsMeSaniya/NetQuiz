package main.processor;

import main.model.Message;
import main.network.PeerConnection;

import java.util.function.Consumer;

/**
 * Processor for TEXT messages
 * Follows Open/Closed Principle
 */
public class TextMessageProcessor implements MessageProcessor {
    private final Consumer<String> chatLogger;
    
    public TextMessageProcessor(Consumer<String> chatLogger) {
        this.chatLogger = chatLogger;
    }
    
    @Override
    public void process(Message message, PeerConnection connection) {
        String displayMessage = message.getSender() + ": " + message.getContent();
        chatLogger.accept(displayMessage);
    }
    
    @Override
    public Message.MessageType getHandledType() {
        return Message.MessageType.TEXT;
    }
}
