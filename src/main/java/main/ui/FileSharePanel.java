package main.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Panel for displaying and managing file sharing
 */
public class FileSharePanel extends JPanel {
    private DefaultListModel<String> fileListModel;
    private JList<String> fileList;
    
    public FileSharePanel() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(15, 15, 15, 15));
        
        initComponents();
    }
    
    private void initComponents() {
        // Title
        JLabel titleLabel = new JLabel("Shared Files");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setBorder(new EmptyBorder(0, 0, 10, 0));
        
        // File list
        fileListModel = new DefaultListModel<>();
        fileList = new JList<>(fileListModel);
        fileList.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(fileList);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(Color.WHITE);
        
        JButton shareButton = new JButton("Share File");
        shareButton.setBackground(new Color(66, 133, 244));
        shareButton.setForeground(Color.WHITE);
        shareButton.setFocusPainted(false);
        
        JButton downloadButton = new JButton("Download");
        downloadButton.setBackground(new Color(52, 168, 83));
        downloadButton.setForeground(Color.WHITE);
        downloadButton.setFocusPainted(false);
        
        JButton deleteButton = new JButton("Delete");
        deleteButton.setBackground(new Color(234, 67, 53));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFocusPainted(false);
        
        buttonPanel.add(shareButton);
        buttonPanel.add(downloadButton);
        buttonPanel.add(deleteButton);
        
        add(titleLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    public void addFile(String fileName) {
        fileListModel.addElement(fileName);
    }
    
    public void removeFile(String fileName) {
        fileListModel.removeElement(fileName);
    }
    
    public void clearFiles() {
        fileListModel.clear();
    }
}
