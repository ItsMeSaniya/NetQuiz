package main.processor;

import main.model.FileTransfer;
import main.model.Message;
import main.network.PeerConnection;

import javax.swing.*;
import java.util.function.BiConsumer;

/**
 * Processor for FILE message type
 * Handles file transfer reception and confirmation
 */
public class FileMessageProcessor implements MessageProcessor {
    private final BiConsumer<FileTransfer, String> fileReceiver;
    private final JFrame parentFrame;
    
    /**
     * Constructor
     * @param fileReceiver Callback to handle received file (fileTransfer, senderName)
     * @param parentFrame Parent frame for dialogs
     */
    public FileMessageProcessor(BiConsumer<FileTransfer, String> fileReceiver, JFrame parentFrame) {
        this.fileReceiver = fileReceiver;
        this.parentFrame = parentFrame;
    }
    
    @Override
    public void process(Message message, PeerConnection connection) {
        if (message.getFileTransfer() != null) {
            FileTransfer fileTransfer = message.getFileTransfer();
            String sender = message.getSender();
            
            // Show confirmation dialog
            SwingUtilities.invokeLater(() -> {
                int confirm = JOptionPane.showConfirmDialog(
                    parentFrame,
                    "File received from " + sender + "\n\n" +
                    "File: " + fileTransfer.getFileName() + "\n" +
                    "Size: " + formatFileSize(fileTransfer.getFileSize()) + "\n\n" +
                    "Do you want to save this file?",
                    "File Received",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
                );
                
                if (confirm == JOptionPane.YES_OPTION) {
                    fileReceiver.accept(fileTransfer, sender);
                }
            });
        }
    }
    
    @Override
    public Message.MessageType getHandledType() {
        return Message.MessageType.FILE;
    }
    
    private String formatFileSize(long bytes) {
        if (bytes < 1024) return bytes + " B";
        if (bytes < 1024 * 1024) return String.format("%.2f KB", bytes / 1024.0);
        return String.format("%.2f MB", bytes / (1024.0 * 1024.0));
    }
}
