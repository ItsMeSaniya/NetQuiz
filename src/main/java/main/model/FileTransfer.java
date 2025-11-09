package main.model;

import java.io.Serializable;

/**
 * FileTransfer model for sharing files between peers
 */
public class FileTransfer implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String fileName;
    private long fileSize;
    private byte[] fileData;
    private String sender;
    private String recipient;
    
    public FileTransfer() {
    }
    
    public FileTransfer(String fileName, long fileSize, byte[] fileData, String sender, String recipient) {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.fileData = fileData;
        this.sender = sender;
        this.recipient = recipient;
    }
    
    // Getters and Setters
    public String getFileName() {
        return fileName;
    }
    
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    public long getFileSize() {
        return fileSize;
    }
    
    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }
    
    public byte[] getFileData() {
        return fileData;
    }
    
    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }
    
    public String getSender() {
        return sender;
    }
    
    public void setSender(String sender) {
        this.sender = sender;
    }
    
    public String getRecipient() {
        return recipient;
    }
    
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
    
    public String getFileSizeFormatted() {
        if (fileSize < 1024) {
            return fileSize + " B";
        } else if (fileSize < 1024 * 1024) {
            return String.format("%.2f KB", fileSize / 1024.0);
        } else {
            return String.format("%.2f MB", fileSize / (1024.0 * 1024.0));
        }
    }
}
