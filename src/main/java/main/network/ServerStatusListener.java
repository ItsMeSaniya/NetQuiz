package main.network;

/**
 * Interface for server status updates
 * Follows Interface Segregation Principle
 */
public interface ServerStatusListener {
    /**
     * Called when server status changes
     * @param status The status message
     */
    void onServerStatus(String status);
}
