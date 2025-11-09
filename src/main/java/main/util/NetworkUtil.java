package main.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Network utility class for common networking operations
 */
public class NetworkUtil {
    
    /**
     * Get the local IP address of the machine
     */
    public static String getLocalIPAddress() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();
                
                // Skip loopback and inactive interfaces
                if (iface.isLoopback() || !iface.isUp()) {
                    continue;
                }
                
                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    
                    // Return the first IPv4 address found
                    if (addr.getAddress().length == 4) {
                        return addr.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        
        // Fallback to localhost
        return "127.0.0.1";
    }
    
    /**
     * Validate IP address format
     */
    public static boolean isValidIPAddress(String ip) {
        if (ip == null || ip.isEmpty()) {
            return false;
        }
        
        String[] parts = ip.split("\\.");
        if (parts.length != 4) {
            return false;
        }
        
        try {
            for (String part : parts) {
                int num = Integer.parseInt(part);
                if (num < 0 || num > 255) {
                    return false;
                }
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    /**
     * Validate port number
     */
    public static boolean isValidPort(int port) {
        return port >= 1024 && port <= 65535;
    }
}
