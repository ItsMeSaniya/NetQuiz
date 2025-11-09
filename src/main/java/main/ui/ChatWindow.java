package main.ui;

import main.model.Message;
import main.network.Client;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Dedicated chat window for one-on-one conversations
 */
public class ChatWindow extends JFrame {
    private JTextArea chatArea;
    private JTextField messageField;
    private JButton sendButton;
    private Client client;
    private String peerName;
    private String currentUser;
    
    public ChatWindow(String currentUser, String peerName, Client client) {
        this.currentUser = currentUser;
        this.peerName = peerName;
        this.client = client;
        
        initComponents();
        setLocationRelativeTo(null);
    }
    
    private void initComponents() {
        setTitle("Chat with " + peerName);
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        
        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(66, 133, 244));
        headerPanel.setBorder(new EmptyBorder(10, 15, 10, 15));
        
        JLabel titleLabel = new JLabel("Chat with " + peerName);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel, BorderLayout.WEST);
        
        // Chat area
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        JScrollPane chatScrollPane = new JScrollPane(chatArea);
        
        // Input panel
        JPanel inputPanel = new JPanel(new BorderLayout(10, 0));
        inputPanel.setBackground(Color.WHITE);
        inputPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        
        messageField = new JTextField();
        messageField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        messageField.addActionListener(e -> sendMessage());
        
        sendButton = new JButton("Send");
        sendButton.setBackground(new Color(66, 133, 244));
        sendButton.setForeground(Color.WHITE);
        sendButton.setFocusPainted(false);
        sendButton.setPreferredSize(new Dimension(80, 35));
        sendButton.addActionListener(e -> sendMessage());
        
        inputPanel.add(messageField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(chatScrollPane, BorderLayout.CENTER);
        mainPanel.add(inputPanel, BorderLayout.SOUTH);
        
        setContentPane(mainPanel);
    }
    
    private void sendMessage() {
        String text = messageField.getText().trim();
        if (text.isEmpty()) {
            return;
        }
        
        Message message = new Message(currentUser, peerName, text, Message.MessageType.TEXT);
        client.sendMessage(message);
        
        appendToChat("You: " + text);
        messageField.setText("");
    }
    
    public void appendToChat(String text) {
        SwingUtilities.invokeLater(() -> {
            chatArea.append(text + "\n");
            chatArea.setCaretPosition(chatArea.getDocument().getLength());
        });
    }
}
