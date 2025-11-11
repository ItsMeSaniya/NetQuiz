package main.ui;

import com.formdev.flatlaf.FlatIntelliJLaf;
import main.model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Login and Registration Frame with modern UI
 */
public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private JButton loginButton;
    private JButton registerButton;
    private JButton switchModeButton;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private Map<String, User> userDatabase;
    private boolean isLoginMode = true;
    
    public LoginFrame() {
        userDatabase = new HashMap<>();
        // Add a default user for testing
        userDatabase.put("admin", new User("admin", "admin", "admin@studyconnect.com"));
        
        initComponents();
        setLocationRelativeTo(null);
    }
    
    private void initComponents() {
        setTitle("StudyConnect - Login");
        setSize(450, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 245, 245));
        
        // Header
        JPanel headerPanel = createHeaderPanel();
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        
        // Card layout for login/register forms
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setOpaque(false);
        
        cardPanel.add(createLoginPanel(), "login");
        cardPanel.add(createRegisterPanel(), "register");
        
        mainPanel.add(cardPanel, BorderLayout.CENTER);
        
        setContentPane(mainPanel);
    }
    
    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(66, 133, 244));
        panel.setBorder(new EmptyBorder(30, 20, 30, 20));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel titleLabel = new JLabel("StudyConnect");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel subtitleLabel = new JLabel("Peer-to-Peer Study Collaboration");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subtitleLabel.setForeground(new Color(230, 230, 230));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(subtitleLabel);
        
        return panel;
    }
    
    private JPanel createLoginPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(40, 50, 40, 50));
        panel.setOpaque(false);
        
        // Title
        JLabel titleLabel = new JLabel("Sign In");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(30));
        
        // Username Label
        JLabel userLabel = new JLabel("Username");
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(userLabel);
        panel.add(Box.createVerticalStrut(5));
        
        // Username Field
        usernameField = new JTextField();
        usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        usernameField.setMaximumSize(new Dimension(300, 35));
        usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameField.setHorizontalAlignment(JTextField.CENTER);
        panel.add(usernameField);
        panel.add(Box.createVerticalStrut(20));
        
        // Password Label
        JLabel passLabel = new JLabel("Password");
        passLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(passLabel);
        panel.add(Box.createVerticalStrut(5));
        
        // Password Field
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passwordField.setMaximumSize(new Dimension(300, 35));
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordField.setHorizontalAlignment(JTextField.CENTER);
        panel.add(passwordField);
        panel.add(Box.createVerticalStrut(30));
        
        // Login button
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        loginButton.setBackground(new Color(66, 133, 244));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setMaximumSize(new Dimension(300, 45));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.addActionListener(e -> handleLogin());
        panel.add(loginButton);
        panel.add(Box.createVerticalStrut(20));
        
        // Switch to register
        JPanel switchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        switchPanel.setOpaque(false);
        switchPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel switchLabel = new JLabel("Don't have an account? ");
        switchLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        switchModeButton = new JButton("Register");
        switchModeButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        switchModeButton.setForeground(new Color(66, 133, 244));
        switchModeButton.setBorderPainted(false);
        switchModeButton.setContentAreaFilled(false);
        switchModeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        switchModeButton.addActionListener(e -> switchToRegister());
        switchPanel.add(switchLabel);
        switchPanel.add(switchModeButton);
        panel.add(switchPanel);
        
        return panel;
    }
    
    private JPanel createRegisterPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(40, 50, 40, 50));
        panel.setOpaque(false);
        
        // Title
        JLabel titleLabel = new JLabel("Create Account");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(30));
        
        // Username
        JLabel userLabel = new JLabel("Username");
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(userLabel);
        panel.add(Box.createVerticalStrut(5));
        
        JTextField regUsernameField = new JTextField();
        regUsernameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        regUsernameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        panel.add(regUsernameField);
        panel.add(Box.createVerticalStrut(15));
        
        // Email
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(emailLabel);
        panel.add(Box.createVerticalStrut(5));
        
        emailField = new JTextField();
        emailField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        emailField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        panel.add(emailField);
        panel.add(Box.createVerticalStrut(15));
        
        // Password
        JLabel passLabel = new JLabel("Password");
        passLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(passLabel);
        panel.add(Box.createVerticalStrut(5));
        
        JPasswordField regPasswordField = new JPasswordField();
        regPasswordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        regPasswordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        panel.add(regPasswordField);
        panel.add(Box.createVerticalStrut(25));
        
        // Register button
        registerButton = new JButton("Create Account");
        registerButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        registerButton.setBackground(new Color(52, 168, 83));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerButton.addActionListener(e -> handleRegister(regUsernameField, regPasswordField, emailField));
        panel.add(registerButton);
        panel.add(Box.createVerticalStrut(15));
        
        // Switch to login
        JPanel switchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        switchPanel.setOpaque(false);
        JLabel switchLabel = new JLabel("Already have an account? ");
        switchLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JButton switchButton = new JButton("Login");
        switchButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        switchButton.setForeground(new Color(66, 133, 244));
        switchButton.setBorderPainted(false);
        switchButton.setContentAreaFilled(false);
        switchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        switchButton.addActionListener(e -> switchToLogin());
        switchPanel.add(switchLabel);
        switchPanel.add(switchButton);
        panel.add(switchPanel);
        
        return panel;
    }
    
    private void switchToRegister() {
        cardLayout.show(cardPanel, "register");
        setTitle("StudyConnect - Register");
    }
    
    private void switchToLogin() {
        cardLayout.show(cardPanel, "login");
        setTitle("StudyConnect - Login");
    }
    
    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());
        
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please enter both username and password", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        User user = userDatabase.get(username);
        if (user != null && user.getPassword().equals(password)) {
            // Login successful
            dispose();
            SwingUtilities.invokeLater(() -> {
                MainDashboard dashboard = new MainDashboard(user);
                dashboard.setVisible(true);
            });
        } else {
            JOptionPane.showMessageDialog(this, 
                "Invalid username or password", 
                "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void handleRegister(JTextField regUsernameField, JPasswordField regPasswordField, JTextField emailField) {
        String username = regUsernameField.getText().trim();
        String password = new String(regPasswordField.getPassword());
        String email = emailField.getText().trim();
        
        if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please fill in all fields", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (userDatabase.containsKey(username)) {
            JOptionPane.showMessageDialog(this, 
                "Username already exists", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Create new user
        User newUser = new User(username, password, email);
        userDatabase.put(username, newUser);
        
        JOptionPane.showMessageDialog(this, 
            "Account created successfully! Please login.", 
            "Success", JOptionPane.INFORMATION_MESSAGE);
        
        switchToLogin();
    }
}
