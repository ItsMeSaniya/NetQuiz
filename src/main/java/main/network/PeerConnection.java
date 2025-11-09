package main.network;

import main.model.Message;
import main.model.FileTransfer;

import java.io.*;
import java.net.*;

/**
 * PeerConnection handles communication with a single peer
 */
public class PeerConnection implements Runnable {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private MessageHandler messageHandler;
    private boolean running;
    private String peerAddress;
    
    public PeerConnection(Socket socket, MessageHandler messageHandler) {
        this.socket = socket;
        this.messageHandler = messageHandler;
        this.peerAddress = socket.getInetAddress().getHostAddress();
        
        try {
            // Important: Create output stream first to avoid deadlock
            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.out.flush();
            this.in = new ObjectInputStream(socket.getInputStream());
            this.running = true;
        } catch (IOException e) {
            messageHandler.onServerStatus("Error initializing peer connection: " + e.getMessage());
            close();
        }
    }
    
    @Override
    public void run() {
        while (running && !socket.isClosed()) {
            try {
                Object obj = in.readObject();
                
                if (obj instanceof Message) {
                    Message message = (Message) obj;
                    messageHandler.onMessageReceived(message, this);
                } else if (obj instanceof FileTransfer) {
                    FileTransfer fileTransfer = (FileTransfer) obj;
                    messageHandler.onFileReceived(fileTransfer, this);
                }
            } catch (EOFException | SocketException e) {
                // Connection closed
                break;
            } catch (IOException | ClassNotFoundException e) {
                if (running) {
                    messageHandler.onServerStatus("Error reading from peer: " + e.getMessage());
                }
                break;
            }
        }
        
        close();
        messageHandler.onServerStatus("Peer disconnected: " + peerAddress);
    }
    
    /**
     * Send a message to the peer
     */
    public synchronized void sendMessage(Message message) {
        try {
            if (out != null && !socket.isClosed()) {
                out.writeObject(message);
                out.flush();
                out.reset(); // Prevent memory leak
            }
        } catch (IOException e) {
            messageHandler.onServerStatus("Error sending message: " + e.getMessage());
        }
    }
    
    /**
     * Send a file to the peer
     */
    public synchronized void sendFile(FileTransfer fileTransfer) {
        try {
            if (out != null && !socket.isClosed()) {
                out.writeObject(fileTransfer);
                out.flush();
                out.reset();
                messageHandler.onServerStatus("File sent: " + fileTransfer.getFileName());
            }
        } catch (IOException e) {
            messageHandler.onServerStatus("Error sending file: " + e.getMessage());
        }
    }
    
    /**
     * Close the connection
     */
    public void close() {
        running = false;
        
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (socket != null && !socket.isClosed()) socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public String getPeerAddress() {
        return peerAddress;
    }
    
    public boolean isRunning() {
        return running && !socket.isClosed();
    }
}
