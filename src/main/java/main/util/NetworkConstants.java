package main.util;

/**
 * Network configuration constants
 * Follows Don't Repeat Yourself (DRY) principle
 */
public class NetworkConstants {
    // Port Configuration
    public static final int DEFAULT_PORT = 8888;
    public static final int MIN_PORT = 1024;
    public static final int MAX_PORT = 65535;
    
    // File Transfer Limits
    public static final int MAX_FILE_SIZE = 50 * 1024 * 1024; // 50MB
    public static final int BUFFER_SIZE = 8192;
    
    // Connection Settings
    public static final int CONNECTION_TIMEOUT = 10000; // 10 seconds
    public static final int MAX_CONNECTIONS = 50;
    
    // IP Service
    public static final String PUBLIC_IP_SERVICE = "https://api.ipify.org";
    
    private NetworkConstants() {
        // Prevent instantiation
    }
}
