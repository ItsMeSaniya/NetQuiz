package main.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * FileTransferProgressDialog - Shows real-time file transfer progress
 * Contribution: File Sharing Module Implementation
 * 
 * This dialog provides visual feedback during file transfers:
 * - Real-time progress bar
 * - Transfer speed calculation
 * - File size and remaining time display
 * - Cancel operation support
 * 
 * Similar to how NotificationPopup provides instant visual feedback for
 * system events, this dialog ensures users are informed about ongoing
 * file transfer operations.
 */
public class FileTransferProgressDialog extends JDialog {
    private JProgressBar progressBar;
    private JLabel statusLabel;
    private JLabel speedLabel;
    private JLabel fileInfoLabel;
    private JButton cancelButton;
    
    private String fileName;
    private long totalBytes;
    private long startTime;
    private boolean cancelled = false;
    
    public FileTransferProgressDialog(Frame parent, String fileName, long totalBytes, boolean isUpload) {
        super(parent, isUpload ? "Uploading File" : "Downloading File", false);
        
        this.fileName = fileName;
        this.totalBytes = totalBytes;
        this.startTime = System.currentTimeMillis();
        
        initComponents(isUpload);
        
        setSize(450, 200);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    }
    
    private void initComponents(boolean isUpload) {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);
        
        // Icon and File Info
        JPanel headerPanel = new JPanel(new BorderLayout(10, 5));
        headerPanel.setBackground(Color.WHITE);
        
        JLabel iconLabel = new JLabel(isUpload ? "📤" : "📥");
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 40));
        
        JPanel infoPanel = new JPanel(new GridLayout(2, 1, 0, 5));
        infoPanel.setBackground(Color.WHITE);
        
        fileInfoLabel = new JLabel(fileName);
        fileInfoLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        statusLabel = new JLabel(isUpload ? "Uploading..." : "Downloading...");
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        statusLabel.setForeground(Color.GRAY);
        
        infoPanel.add(fileInfoLabel);
        infoPanel.add(statusLabel);
        
        headerPanel.add(iconLabel, BorderLayout.WEST);
        headerPanel.add(infoPanel, BorderLayout.CENTER);
        
        // Progress Panel
        JPanel progressPanel = new JPanel(new GridLayout(3, 1, 0, 8));
        progressPanel.setBackground(Color.WHITE);
        
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressBar.setString("0%");
        progressBar.setPreferredSize(new Dimension(0, 25));
        progressBar.setForeground(new Color(52, 152, 219));
        
        speedLabel = new JLabel("Speed: Calculating...");
        speedLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        speedLabel.setForeground(Color.GRAY);
        
        JLabel sizeLabel = new JLabel(String.format("Total Size: %s", formatBytes(totalBytes)));
        sizeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        sizeLabel.setForeground(Color.GRAY);
        
        progressPanel.add(progressBar);
        progressPanel.add(speedLabel);
        progressPanel.add(sizeLabel);
        
        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.WHITE);
        
        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        cancelButton.setFocusPainted(false);
        cancelButton.addActionListener(e -> {
            cancelled = true;
            dispose();
        });
        
        buttonPanel.add(cancelButton);
        
        // Add all to main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(progressPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    /**
     * Update progress
     * @param bytesTransferred Number of bytes transferred
     */
    public void updateProgress(long bytesTransferred) {
        SwingUtilities.invokeLater(() -> {
            int progress = (int) ((bytesTransferred * 100) / totalBytes);
            progressBar.setValue(progress);
            progressBar.setString(progress + "%");
            
            // Calculate speed
            long elapsed = System.currentTimeMillis() - startTime;
            if (elapsed > 0) {
                double speed = (bytesTransferred / 1024.0) / (elapsed / 1000.0); // KB/s
                String speedText;
                if (speed > 1024) {
                    speedText = String.format("Speed: %.2f MB/s", speed / 1024.0);
                } else {
                    speedText = String.format("Speed: %.2f KB/s", speed);
                }
                
                // Estimate remaining time
                long remaining = totalBytes - bytesTransferred;
                double remainingSeconds = remaining / (speed * 1024.0);
                
                if (remainingSeconds < 60) {
                    speedText += String.format(" | %d seconds remaining", (int) remainingSeconds);
                } else {
                    speedText += String.format(" | %d minutes remaining", (int) (remainingSeconds / 60));
                }
                
                speedLabel.setText(speedText);
            }
            
            statusLabel.setText(String.format("%s / %s", 
                formatBytes(bytesTransferred), 
                formatBytes(totalBytes)));
        });
    }
    
    /**
     * Mark transfer as complete
     */
    public void setComplete() {
        SwingUtilities.invokeLater(() -> {
            progressBar.setValue(100);
            progressBar.setString("Complete");
            progressBar.setForeground(new Color(46, 204, 113));
            statusLabel.setText("✅ Transfer completed successfully!");
            cancelButton.setText("Close");
            
            // Auto-close after 2 seconds
            Timer timer = new Timer(2000, e -> dispose());
            timer.setRepeats(false);
            timer.start();
        });
    }
    
    /**
     * Mark transfer as failed
     */
    public void setFailed(String error) {
        SwingUtilities.invokeLater(() -> {
            progressBar.setForeground(new Color(231, 76, 60));
            statusLabel.setText("❌ Transfer failed: " + error);
            statusLabel.setForeground(Color.RED);
            cancelButton.setText("Close");
        });
    }
    
    /**
     * Check if transfer was cancelled
     */
    public boolean isCancelled() {
        return cancelled;
    }
    
    private String formatBytes(long bytes) {
        if (bytes < 1024) {
            return bytes + " B";
        } else if (bytes < 1024 * 1024) {
            return String.format("%.2f KB", bytes / 1024.0);
        } else {
            return String.format("%.2f MB", bytes / (1024.0 * 1024.0));
        }
    }
}
