package main.network;

/**
 * Interface for connection error handling
 * Follows Interface Segregation Principle
 */
public interface ConnectionErrorListener {
    /**
     * Called when a connection error occurs
     * @param error The error message
     */
    void onConnectionError(String error);
}
