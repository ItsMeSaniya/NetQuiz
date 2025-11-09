package main;

import com.formdev.flatlaf.FlatIntelliJLaf;
import main.ui.LoginFrame;

import javax.swing.*;

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
        
        // Launch the login frame
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        });
    }
}
