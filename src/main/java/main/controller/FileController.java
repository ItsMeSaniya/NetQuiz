package main.controller;

import main.model.FileTransfer;
import main.model.Message;
import main.model.User;
import main.network.Client;
import main.network.PeerConnection;
import main.util.NetworkConstants;

import javax.swing.*;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Controller for file transfer operations
 * Follows Single Responsibility Principle
 */
public class FileController {
    private final User currentUser;
    private final List<Client> connectedPeers;
    private final Map<String, PeerConnection> peerConnections;
    private final Consumer<String> chatLogger;
    private final JFrame parentFrame;
    
    public FileController(User currentUser,
                         List<Client> connectedPeers,
                         Map<String, PeerConnection> peerConnections,
                         Consumer<String> chatLogger,
                         JFrame parentFrame) {
        this.currentUser = currentUser;
        this.connectedPeers = connectedPeers;
        this.peerConnections = peerConnections;
        this.chatLogger = chatLogger;
        this.parentFrame = parentFrame;
    }
    
    /**
     * Select and send a file to a user
     * @param targetUser The target user
     */
    public void selectAndSendFile(String targetUser) {
        if (targetUser == null || targetUser.equals("Select recipient...")) {
            JOptionPane.showMessageDialog(parentFrame,
                "Please select a recipient first",
                "No Recipient", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select File to Send to " + targetUser);
        
        int result = fileChooser.showOpenDialog(parentFrame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            
            // Check file size
            if (selectedFile.length() > NetworkConstants.MAX_FILE_SIZE) {
                JOptionPane.showMessageDialog(parentFrame,
                    "File too large! Maximum: " + formatFileSize(NetworkConstants.MAX_FILE_SIZE),
                    "File Too Large", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Confirm sending
            int confirm = JOptionPane.showConfirmDialog(parentFrame,
                "Send file to " + targetUser + "?\n\n" +
                "File: " + selectedFile.getName() + "\n" +
                "Size: " + formatFileSize(selectedFile.length()) + "\n" +
                "Recipient: " + targetUser,
                "Confirm File Send",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
            
            if (confirm == JOptionPane.YES_OPTION) {
                sendFile(targetUser, selectedFile);
            }
        }
    }
    
    /**
     * Send file to a specific user
     * @param targetUser The target user
     * @param file The file to send
     */
    private void sendFile(String targetUser, File file) {
        try {
            byte[] fileData = java.nio.file.Files.readAllBytes(file.toPath());
            
            FileTransfer fileTransfer = new FileTransfer(
                file.getName(),
                file.length(),
                fileData,
                currentUser.getUsername(),
                targetUser
            );
            
            Message fileMessage = new Message(
                currentUser.getUsername(),
                targetUser,
                "Sending file: " + file.getName(),
                Message.MessageType.FILE
            );
            fileMessage.setFileTransfer(fileTransfer);
            
            boolean isAdmin = isUserAdmin();
            
            if (isAdmin) {
                PeerConnection targetConnection = peerConnections.get(targetUser);
                if (targetConnection != null) {
                    targetConnection.sendMessage(fileMessage);
                    chatLogger.accept("[FILE] Sent '" + file.getName() + "' to " + targetUser + 
                        " (" + formatFileSize(file.length()) + ")");
                } else {
                    JOptionPane.showMessageDialog(parentFrame,
                        "Target user not found!",
                        "Send Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                if (!connectedPeers.isEmpty()) {
                    connectedPeers.get(0).sendMessage(fileMessage);
                    chatLogger.accept("[FILE] Sent '" + file.getName() + "' to " + targetUser + 
                        " (" + formatFileSize(file.length()) + ")");
                } else {
                    JOptionPane.showMessageDialog(parentFrame,
                        "Not connected to server!",
                        "Connection Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parentFrame,
                "Failed to send file: " + e.getMessage(),
                "File Send Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    /**
     * Save a received file
     * @param fileTransfer The file transfer data
     */
    public void saveReceivedFile(FileTransfer fileTransfer) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Received File");
        fileChooser.setSelectedFile(new File(fileTransfer.getFileName()));
        
        int result = fileChooser.showSaveDialog(parentFrame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File saveFile = fileChooser.getSelectedFile();
            try {
                java.nio.file.Files.write(saveFile.toPath(), fileTransfer.getFileData());
                JOptionPane.showMessageDialog(parentFrame,
                    "File saved successfully!\n" + saveFile.getAbsolutePath(),
                    "File Saved", JOptionPane.INFORMATION_MESSAGE);
                chatLogger.accept("[FILE] Saved as: " + saveFile.getName());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(parentFrame,
                    "Failed to save file: " + e.getMessage(),
                    "Save Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    /**
     * Format file size for display
     * @param bytes File size in bytes
     * @return Formatted string
     */
    public static String formatFileSize(long bytes) {
        if (bytes < 1024) return bytes + " B";
        if (bytes < 1024 * 1024) return String.format("%.2f KB", bytes / 1024.0);
        return String.format("%.2f MB", bytes / (1024.0 * 1024.0));
    }
    
    /**
     * Check if current user is admin
     * @return true if admin
     */
    private boolean isUserAdmin() {
        return currentUser.getUsername().equalsIgnoreCase("admin") && 
               currentUser.getPassword().equals("admin");
    }
}
