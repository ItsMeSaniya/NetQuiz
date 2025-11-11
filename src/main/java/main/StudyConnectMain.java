package main;

import com.formdev.flatlaf.FlatIntelliJLaf;
import main.ui.LoginFrame;
import main.util.NetworkUtil;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Main entry point for StudyConnect application
 */
public class StudyConnectMain {
    
    public static void main(String[] args) {
        // Set FlatLaf look and feel for modern UI
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
            
            // Customize UI defaults
            UIManager.put("Button.arc", 8);
            UIManager.put("Component.arc", 8);
            UIManager.put("TextComponent.arc", 8);
            UIManager.put("Component.focusWidth", 1);
            UIManager.put("Button.font", new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));
            UIManager.put("Label.font", new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));
            
        } catch (Exception e) {
            System.err.println("Failed to initialize FlatLaf Look and Feel");
            e.printStackTrace();
            // Fallback to system look and feel
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
        // Display network information
        System.out.println("=======================================================");
        System.out.println("       StudyConnect - Network Information");
        System.out.println("=======================================================");
        String localIP = NetworkUtil.getLocalIPAddress();
        String publicIP = getPublicIP();
        
        System.out.println("Local IP Address:  " + localIP);
        System.out.println("Public IP Address: " + publicIP);
        System.out.println("=======================================================");
        System.out.println("\n📡 CONNECTION METHODS:");
        System.out.println("\n1. LOCAL NETWORK (Same WiFi/Router):");
        System.out.println("   - Friends on same network connect to: " + localIP + ":8888");
        System.out.println("\n2. INTERNET (Different Networks/Routers):");
        System.out.println("   - Setup port forwarding on your router:");
        System.out.println("     * Forward external port 8888 -> " + localIP + ":8888");
        System.out.println("     * Allow port 8888 in Windows Firewall");
        System.out.println("   - Friends connect to: " + publicIP + ":8888");
        System.out.println("\n=======================================================");
        
        // Launch the login frame
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        });
    }
    
    /**
     * Get public IP address from external service
     */
    private static String getPublicIP() {
        try {
            URL url = new URL("https://api.ipify.org");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String ip = reader.readLine();
            reader.close();
            return ip;
        } catch (Exception e) {
            return "Unable to detect (check internet)";
        }
    }
}
