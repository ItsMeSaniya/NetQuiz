package main.processor;

import main.model.Message;
import main.network.PeerConnection;

import java.util.function.Consumer;

/**
 * Processor for PEER_LIST message type
 * Updates UI with list of available peers
 */
public class PeerListMessageProcessor implements MessageProcessor {
    private final Consumer<String> peerListUpdater;
    
    /**
     * Constructor
     * @param peerListUpdater Callback to update peer list (comma-separated peer names)
     */
    public PeerListMessageProcessor(Consumer<String> peerListUpdater) {
        this.peerListUpdater = peerListUpdater;
    }
    
    @Override
    public void process(Message message, PeerConnection connection) {
        String peerList = message.getContent();
        if (peerList != null && !peerList.isEmpty()) {
            peerListUpdater.accept(peerList);
        }
    }
    
    @Override
    public Message.MessageType getHandledType() {
        return Message.MessageType.PEER_LIST;
    }
}
