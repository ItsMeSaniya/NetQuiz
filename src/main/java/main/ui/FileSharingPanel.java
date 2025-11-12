package main.ui;

import main.model.FileTransfer;
import main.network.FileTransferManager;
import main.util.FileUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.util.List;

/**
 * File Sharing Panel - Dedicated UI for P2P file sharing
 * Contribution: File Sharing Module Implementation
 * 
 * This panel provides a comprehensive file sharing interface including:
 * - File selection and upload to peers
 * - Real-time transfer progress tracking
 * - Download history with file details
 * - Peer-to-peer file distribution
 * - Automatic retry on failure
 */
public class FileSharingPanel extends JPanel {
    private JTable fileHistoryTable;
    private DefaultTableModel tableModel;
    private JButton selectFileButton;
    private JButton sendToAllButton;
    private JButton sendToSelectedButton;
    private JComboBox<String> peerSelector;
    private JLabel selectedFileLabel;
    private JProgressBar uploadProgress;
    private JLabel statusLabel;
    
    private File selectedFile;
    private FileTransferManager transferManager;
    private String currentUsername;
    
    public FileSharingPanel(String currentUsername, FileTransferManager transferManager) {
        this.currentUsername = currentUsername;
        this.transferManager = transferManager;
        
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(15, 15, 15, 15));
        setBackground(Color.WHITE);
        
        initComponents();
    }
    
    private void initComponents() {
        // Header Panel
        JPanel headerPanel = createHeaderPanel();
        add(headerPanel, BorderLayout.NORTH);
        
        // Center Panel - File Selection and Controls
        JPanel centerPanel = createCenterPanel();
        add(centerPanel, BorderLayout.CENTER);
        
        // Bottom Panel - File History Table
        JPanel bottomPanel = createBottomPanel();
        add(bottomPanel, BorderLayout.SOUTH);
    }
    
    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(new EmptyBorder(0, 0, 15, 0));
        
        JLabel titleLabel = new JLabel("📁 File Sharing Module");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(41, 128, 185));
        
        JLabel subtitleLabel = new JLabel("Share files instantly with connected peers");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitleLabel.setForeground(Color.GRAY);
        
        JPanel textPanel = new JPanel(new GridLayout(2, 1, 0, 5));
        textPanel.setBackground(Color.WHITE);
        textPanel.add(titleLabel);
        textPanel.add(subtitleLabel);
        
        panel.add(textPanel, BorderLayout.WEST);
        
        return panel;
    }
    
    private JPanel createCenterPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        
        // File Selection Panel
        JPanel fileSelectionPanel = new JPanel(new BorderLayout(10, 10));
        fileSelectionPanel.setBackground(Color.WHITE);
        fileSelectionPanel.setBorder(BorderFactory.createTitledBorder(
            new LineBorder(new Color(41, 128, 185), 2),
            "Select File to Share",
            0,
            0,
            new Font("Segoe UI", Font.BOLD, 14),
            new Color(41, 128, 185)
        ));
        
        // File info panel
        JPanel fileInfoPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        fileInfoPanel.setBackground(Color.WHITE);
        
        selectedFileLabel = new JLabel("No file selected");
        selectedFileLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        uploadProgress = new JProgressBar(0, 100);
        uploadProgress.setStringPainted(true);
        uploadProgress.setPreferredSize(new Dimension(0, 25));
        uploadProgress.setForeground(new Color(46, 204, 113));
        uploadProgress.setVisible(false);
        
        statusLabel = new JLabel(" ");
        statusLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        statusLabel.setForeground(Color.GRAY);
        
        fileInfoPanel.add(selectedFileLabel);
        fileInfoPanel.add(uploadProgress);
        fileInfoPanel.add(statusLabel);
        
        fileSelectionPanel.add(fileInfoPanel, BorderLayout.CENTER);
        
        // Buttons panel
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        buttonsPanel.setBackground(Color.WHITE);
        
        selectFileButton = new JButton("📂 Browse Files");
        selectFileButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        selectFileButton.setBackground(new Color(52, 152, 219));
        selectFileButton.setForeground(Color.WHITE);
        selectFileButton.setFocusPainted(false);
        selectFileButton.setPreferredSize(new Dimension(150, 40));
        selectFileButton.addActionListener(e -> selectFile());
        
        buttonsPanel.add(selectFileButton);
        
        fileSelectionPanel.add(buttonsPanel, BorderLayout.SOUTH);
        
        // Peer Selection Panel
        JPanel peerPanel = new JPanel(new BorderLayout(10, 10));
        peerPanel.setBackground(Color.WHITE);
        peerPanel.setBorder(BorderFactory.createTitledBorder(
            new LineBorder(new Color(41, 128, 185), 2),
            "Send To",
            0,
            0,
            new Font("Segoe UI", Font.BOLD, 14),
            new Color(41, 128, 185)
        ));
        
        JPanel peerControlPanel = new JPanel(new BorderLayout(10, 10));
        peerControlPanel.setBackground(Color.WHITE);
        
        peerSelector = new JComboBox<>();
        peerSelector.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        peerSelector.setPreferredSize(new Dimension(0, 35));
        peerSelector.addItem("Select a peer...");
        
        peerControlPanel.add(peerSelector, BorderLayout.CENTER);
        
        JPanel sendButtonsPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        sendButtonsPanel.setBackground(Color.WHITE);
        
        sendToSelectedButton = new JButton("📤 Send to Selected");
        sendToSelectedButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        sendToSelectedButton.setBackground(new Color(46, 204, 113));
        sendToSelectedButton.setForeground(Color.WHITE);
        sendToSelectedButton.setFocusPainted(false);
        sendToSelectedButton.setPreferredSize(new Dimension(0, 40));
        sendToSelectedButton.setEnabled(false);
        sendToSelectedButton.addActionListener(e -> sendToSelectedPeer());
        
        sendToAllButton = new JButton("📢 Send to All Peers");
        sendToAllButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        sendToAllButton.setBackground(new Color(155, 89, 182));
        sendToAllButton.setForeground(Color.WHITE);
        sendToAllButton.setFocusPainted(false);
        sendToAllButton.setPreferredSize(new Dimension(0, 40));
        sendToAllButton.setEnabled(false);
        sendToAllButton.addActionListener(e -> sendToAllPeers());
        
        sendButtonsPanel.add(sendToSelectedButton);
        sendButtonsPanel.add(sendToAllButton);
        
        peerControlPanel.add(sendButtonsPanel, BorderLayout.SOUTH);
        
        peerPanel.add(peerControlPanel, BorderLayout.CENTER);
        
        // Combine panels
        JPanel combinedPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        combinedPanel.setBackground(Color.WHITE);
        combinedPanel.add(fileSelectionPanel);
        combinedPanel.add(peerPanel);
        
        panel.add(combinedPanel, BorderLayout.NORTH);
        
        return panel;
    }
    
    private JPanel createBottomPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder(
            new LineBorder(new Color(41, 128, 185), 2),
            "File Transfer History",
            0,
            0,
            new Font("Segoe UI", Font.BOLD, 14),
            new Color(41, 128, 185)
        ));
        
        // Create table
        String[] columns = {"Time", "File Name", "Size", "Sender", "Recipient", "Status"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        fileHistoryTable = new JTable(tableModel);
        fileHistoryTable.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        fileHistoryTable.setRowHeight(25);
        fileHistoryTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        fileHistoryTable.getTableHeader().setBackground(new Color(236, 240, 241));
        
        JScrollPane scrollPane = new JScrollPane(fileHistoryTable);
        scrollPane.setPreferredSize(new Dimension(0, 200));
        
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private void selectFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setDialogTitle("Select File to Share");
        
        int result = fileChooser.showOpenDialog(this);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            
            // Check file size (limit to 50MB)
            long maxSize = 50 * 1024 * 1024; // 50MB
            if (selectedFile.length() > maxSize) {
                JOptionPane.showMessageDialog(this,
                    "File is too large! Maximum size is 50MB.\nSelected file: " + 
                    formatFileSize(selectedFile.length()),
                    "File Too Large",
                    JOptionPane.WARNING_MESSAGE);
                selectedFile = null;
                return;
            }
            
            String fileInfo = String.format("📄 %s (%s)",
                selectedFile.getName(),
                formatFileSize(selectedFile.length()));
            
            selectedFileLabel.setText(fileInfo);
            selectedFileLabel.setForeground(new Color(39, 174, 96));
            statusLabel.setText("File ready to send");
            
            sendToSelectedButton.setEnabled(true);
            sendToAllButton.setEnabled(true);
        }
    }
    
    private void sendToSelectedPeer() {
        if (selectedFile == null) {
            JOptionPane.showMessageDialog(this,
                "Please select a file first!",
                "No File Selected",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String selectedPeer = (String) peerSelector.getSelectedItem();
        if (selectedPeer == null || selectedPeer.equals("Select a peer...")) {
            JOptionPane.showMessageDialog(this,
                "Please select a peer!",
                "No Peer Selected",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        sendFile(selectedPeer);
    }
    
    private void sendToAllPeers() {
        if (selectedFile == null) {
            JOptionPane.showMessageDialog(this,
                "Please select a file first!",
                "No File Selected",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        sendFile("all");
    }
    
    private void sendFile(String recipient) {
        new SwingWorker<Boolean, Integer>() {
            @Override
            protected Boolean doInBackground() throws Exception {
                uploadProgress.setVisible(true);
                uploadProgress.setValue(0);
                statusLabel.setText("Reading file...");
                
                // Read file
                byte[] fileData = FileUtil.readFileToBytes(selectedFile);
                publish(30);
                
                statusLabel.setText("Preparing transfer...");
                FileTransfer transfer = new FileTransfer(
                    selectedFile.getName(),
                    selectedFile.length(),
                    fileData,
                    currentUsername,
                    recipient
                );
                publish(50);
                
                // Send through transfer manager
                statusLabel.setText("Sending file...");
                boolean success = transferManager.sendFile(transfer, recipient);
                publish(100);
                
                return success;
            }
            
            @Override
            protected void process(List<Integer> chunks) {
                for (Integer progress : chunks) {
                    uploadProgress.setValue(progress);
                }
            }
            
            @Override
            protected void done() {
                try {
                    boolean success = get();
                    
                    if (success) {
                        statusLabel.setText("✅ File sent successfully!");
                        statusLabel.setForeground(new Color(39, 174, 96));
                        
                        // Add to history
                        addToHistory(
                            new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date()),
                            selectedFile.getName(),
                            formatFileSize(selectedFile.length()),
                            currentUsername,
                            recipient,
                            "✅ Sent"
                        );
                        
                        // Reset
                        Timer timer = new Timer(2000, e -> {
                            uploadProgress.setVisible(false);
                            uploadProgress.setValue(0);
                            statusLabel.setText(" ");
                            selectedFileLabel.setText("No file selected");
                            selectedFileLabel.setForeground(Color.BLACK);
                            selectedFile = null;
                            sendToSelectedButton.setEnabled(false);
                            sendToAllButton.setEnabled(false);
                        });
                        timer.setRepeats(false);
                        timer.start();
                        
                    } else {
                        statusLabel.setText("❌ Failed to send file");
                        statusLabel.setForeground(Color.RED);
                        uploadProgress.setVisible(false);
                    }
                    
                } catch (Exception e) {
                    statusLabel.setText("❌ Error: " + e.getMessage());
                    statusLabel.setForeground(Color.RED);
                    uploadProgress.setVisible(false);
                }
            }
        }.execute();
    }
    
    public void addReceivedFile(FileTransfer transfer) {
        SwingUtilities.invokeLater(() -> {
            addToHistory(
                new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date()),
                transfer.getFileName(),
                formatFileSize(transfer.getFileSize()),
                transfer.getSender(),
                "You",
                "✅ Received"
            );
            
            // Show notification
            JOptionPane.showMessageDialog(this,
                String.format("File received from %s:\n%s (%s)",
                    transfer.getSender(),
                    transfer.getFileName(),
                    formatFileSize(transfer.getFileSize())),
                "File Received",
                JOptionPane.INFORMATION_MESSAGE);
        });
    }
    
    private void addToHistory(String time, String fileName, String size, 
                              String sender, String recipient, String status) {
        tableModel.addRow(new Object[]{time, fileName, size, sender, recipient, status});
        
        // Auto-scroll to latest
        int lastRow = tableModel.getRowCount() - 1;
        if (lastRow >= 0) {
            fileHistoryTable.setRowSelectionInterval(lastRow, lastRow);
            fileHistoryTable.scrollRectToVisible(
                fileHistoryTable.getCellRect(lastRow, 0, true));
        }
    }
    
    public void updatePeerList(java.util.List<String> peers) {
        SwingUtilities.invokeLater(() -> {
            peerSelector.removeAllItems();
            peerSelector.addItem("Select a peer...");
            
            for (String peer : peers) {
                peerSelector.addItem(peer);
            }
        });
    }
    
    private String formatFileSize(long size) {
        if (size < 1024) {
            return size + " B";
        } else if (size < 1024 * 1024) {
            return String.format("%.2f KB", size / 1024.0);
        } else {
            return String.format("%.2f MB", size / (1024.0 * 1024.0));
        }
    }
}
