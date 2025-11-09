package main.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Panel for displaying connected peers
 */
public class PeerListPanel extends JPanel {
    private DefaultListModel<String> peerListModel;
    private JList<String> peerList;
    private List<PeerInfo> peers;
    
    public PeerListPanel() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(15, 15, 15, 15));
        
        peers = new ArrayList<>();
        initComponents();
    }
    
    private void initComponents() {
        // Title
        JLabel titleLabel = new JLabel("Connected Peers");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setBorder(new EmptyBorder(0, 0, 10, 0));
        
        // Peer list
        peerListModel = new DefaultListModel<>();
        peerList = new JList<>(peerListModel);
        peerList.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        peerList.setCellRenderer(new PeerListCellRenderer());
        JScrollPane scrollPane = new JScrollPane(peerList);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(Color.WHITE);
        
        JButton chatButton = new JButton("Open Chat");
        chatButton.setBackground(new Color(66, 133, 244));
        chatButton.setForeground(Color.WHITE);
        chatButton.setFocusPainted(false);
        
        JButton disconnectButton = new JButton("Disconnect");
        disconnectButton.setBackground(new Color(234, 67, 53));
        disconnectButton.setForeground(Color.WHITE);
        disconnectButton.setFocusPainted(false);
        
        buttonPanel.add(chatButton);
        buttonPanel.add(disconnectButton);
        
        add(titleLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    public void addPeer(String peerName, String ipAddress, int port) {
        PeerInfo peerInfo = new PeerInfo(peerName, ipAddress, port);
        peers.add(peerInfo);
        peerListModel.addElement(peerName + " (" + ipAddress + ":" + port + ")");
    }
    
    public void removePeer(String peerName) {
        peers.removeIf(p -> p.name.equals(peerName));
        for (int i = 0; i < peerListModel.size(); i++) {
            if (peerListModel.get(i).startsWith(peerName)) {
                peerListModel.remove(i);
                break;
            }
        }
    }
    
    public void clearPeers() {
        peers.clear();
        peerListModel.clear();
    }
    
    // Inner class for peer information
    private static class PeerInfo {
        String name;
        String ipAddress;
        int port;
        
        PeerInfo(String name, String ipAddress, int port) {
            this.name = name;
            this.ipAddress = ipAddress;
            this.port = port;
        }
    }
    
    // Custom cell renderer for peer list
    private static class PeerListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value,
                                                      int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            label.setBorder(new EmptyBorder(5, 10, 5, 10));
            
            if (isSelected) {
                label.setBackground(new Color(66, 133, 244));
                label.setForeground(Color.WHITE);
            }
            
            return label;
        }
    }
}
