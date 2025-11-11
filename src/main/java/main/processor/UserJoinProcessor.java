package main.processor;

import main.model.Message;
import main.network.PeerConnection;

import java.util.function.Consumer;

/**
 * Processor for USER_JOIN message type
 * Handles user connection notifications
 */
public class UserJoinProcessor implements MessageProcessor {
    private final Consumer<String> statusLogger;
    
    /**
     * Constructor
     * @param statusLogger Callback to log status messages
     */
    public UserJoinProcessor(Consumer<String> statusLogger) {
        this.statusLogger = statusLogger;
    }
    
    @Override
    public void process(Message message, PeerConnection connection) {
        String username = message.getSender();
        String statusMsg = "[STATUS] " + username + " joined the network";
        statusLogger.accept(statusMsg);
    }
    
    @Override
    public Message.MessageType getHandledType() {
        return Message.MessageType.USER_JOIN;
    }
}
