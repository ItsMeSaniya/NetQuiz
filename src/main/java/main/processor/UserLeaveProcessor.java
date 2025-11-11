package main.processor;

import main.model.Message;
import main.network.PeerConnection;

import java.util.function.Consumer;

/**
 * Processor for USER_LEAVE message type
 * Handles user disconnection notifications
 */
public class UserLeaveProcessor implements MessageProcessor {
    private final Consumer<String> statusLogger;
    
    /**
     * Constructor
     * @param statusLogger Callback to log status messages
     */
    public UserLeaveProcessor(Consumer<String> statusLogger) {
        this.statusLogger = statusLogger;
    }
    
    @Override
    public void process(Message message, PeerConnection connection) {
        String username = message.getSender();
        String statusMsg = "[STATUS] " + username + " left the network";
        statusLogger.accept(statusMsg);
    }
    
    @Override
    public Message.MessageType getHandledType() {
        return Message.MessageType.USER_LEAVE;
    }
}
