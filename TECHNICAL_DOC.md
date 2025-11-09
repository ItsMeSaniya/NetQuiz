# StudyConnect - Technical Documentation

## Architecture Overview

StudyConnect is built using a peer-to-peer architecture with the following components:

### 1. Architecture Diagram

```
┌─────────────────────────────────────────────────────────┐
│                    StudyConnect Client                   │
├──────────────┬─────────────────┬────────────────────────┤
│   UI Layer   │  Network Layer  │    Data Layer          │
│              │                 │                        │
│  LoginFrame  │     Server      │    User Model          │
│  Dashboard   │     Client      │    Message Model       │
│  ChatWindow  │  PeerConnection │    FileTransfer Model  │
│  Panels      │  MessageHandler │                        │
└──────────────┴─────────────────┴────────────────────────┘
         │              │                    │
         └──────────────┼────────────────────┘
                        │
                   Socket Layer
                   (TCP/IP)
```

### 2. Package Structure

```
main/
├── StudyConnectMain.java          # Entry point
├── model/                         # Data models
│   ├── User.java                 # User entity
│   ├── Message.java              # Chat message
│   └── FileTransfer.java         # File transfer object
├── network/                       # Networking layer
│   ├── Server.java               # Server socket handler
│   ├── Client.java               # Client connection
│   ├── PeerConnection.java       # Peer communication
│   └── MessageHandler.java       # Event interface
├── ui/                           # User interface
│   ├── LoginFrame.java           # Authentication UI
│   ├── MainDashboard.java        # Main application window
│   ├── ChatWindow.java           # Chat interface
│   ├── FileSharePanel.java       # File sharing UI
│   └── PeerListPanel.java        # Peer management UI
└── util/                         # Utilities
    ├── NetworkUtil.java          # Network helpers
    └── FileUtil.java             # File operations
```

## Networking Implementation

### 1. Server Architecture

**Server.java** - Multi-threaded server for accepting peer connections

```java
Key Features:
- ServerSocket for accepting connections
- ExecutorService for thread pool management
- CopyOnWriteArrayList for thread-safe peer list
- Broadcast capability to all connected peers

Flow:
1. Create ServerSocket on specified port
2. Accept incoming connections in loop
3. Create PeerConnection for each client
4. Execute PeerConnection in thread pool
5. Handle disconnections gracefully
```

### 2. Client Architecture

**Client.java** - Initiates connections to remote peers

```java
Key Features:
- Socket with connection timeout
- Single PeerConnection per client
- Asynchronous message handling
- Automatic reconnection support

Flow:
1. Create Socket with timeout
2. Connect to remote host:port
3. Initialize PeerConnection
4. Start message listening thread
5. Send messages asynchronously
```

### 3. Peer Connection

**PeerConnection.java** - Handles bidirectional communication

```java
Key Features:
- ObjectInputStream/ObjectOutputStream for serialization
- Runnable for continuous message reception
- Synchronized message sending
- Automatic stream reset to prevent memory leaks

Protocol:
1. Establish streams (output first to avoid deadlock)
2. Listen for incoming objects
3. Deserialize and handle messages/files
4. Send responses when needed
5. Close gracefully on disconnect
```

### 4. Message Protocol

**Message Types:**

```java
public enum MessageType {
    TEXT,           // Regular chat message
    FILE,           // File transfer notification
    SYSTEM,         // System notification
    USER_JOIN,      // User joined chat
    USER_LEAVE,     // User left chat
    PEER_REQUEST,   // Request peer information
    PEER_RESPONSE   // Response with peer data
}
```

**Message Format:**

```java
Message {
    String sender;              // Username of sender
    String recipient;           // Target user or "all"
    String content;             // Message content
    MessageType type;           // Message type
    LocalDateTime timestamp;    // Send time
}
```

**File Transfer Format:**

```java
FileTransfer {
    String fileName;            // Original filename
    long fileSize;              // Size in bytes
    byte[] fileData;            // File content
    String sender;              // Sender username
    String recipient;           // Recipient username
}
```

## User Interface Design

### 1. UI Components

**Technology Stack:**

- Java Swing - Core UI framework
- FlatLaf - Modern look and feel
- GridBagLayout, BorderLayout - Layout managers
- Custom renderers for enhanced visuals

**Color Scheme:**

```java
Primary Blue:    #4285F4 (66, 133, 244)
Success Green:   #34A853 (52, 168, 83)
Warning Yellow:  #FBBC05 (251, 188, 5)
Error Red:       #EA4335 (234, 67, 53)
Background:      #F5F5F5 (245, 245, 245)
White:           #FFFFFF (255, 255, 255)
```

### 2. Login Frame

**Features:**

- Card layout for login/register switching
- Password field masking
- Input validation
- In-memory user database
- Modern Material Design inspired UI

**Validation:**

- Non-empty fields
- Unique usernames
- Email format checking
- Password strength (optional)

### 3. Main Dashboard

**Layout:**

```
┌─────────────────────────────────────────────┐
│           Header (User Info)                │
├────────────┬────────────────────────────────┤
│   Side     │                                │
│   Panel    │      Chat Panel                │
│            │                                │
│  Server    │                                │
│  Control   │                                │
│            │                                │
│  Peer      │                                │
│  List      │                                │
└────────────┴────────────────────────────────┘
│           Status Bar                        │
└─────────────────────────────────────────────┘
```

**Components:**

- Header: User greeting and logout
- Side Panel: Server controls and peer management
- Chat Panel: Message display and input
- Status Bar: Real-time status updates

## Data Flow

### 1. Message Sending Flow

```
User Input → UI Event
    ↓
Create Message Object
    ↓
Serialize to ObjectOutputStream
    ↓
Socket Send
    ↓
Network Layer (TCP/IP)
    ↓
Receiver Socket
    ↓
ObjectInputStream Deserialize
    ↓
MessageHandler Event
    ↓
Update UI (SwingUtilities.invokeLater)
```

### 2. File Transfer Flow

```
Select File → File Chooser
    ↓
Read File to byte[]
    ↓
Create FileTransfer Object
    ↓
Serialize and Send
    ↓
Receiver Deserializes
    ↓
Show Confirmation Dialog
    ↓
User Accepts
    ↓
Write bytes to File
    ↓
Notify Success
```

### 3. Connection Flow

```
Server Mode:
Start Server → ServerSocket.accept()
    ↓
New Connection → Create PeerConnection
    ↓
Add to Connection List
    ↓
Start Listening Thread
    ↓
Handle Messages
    ↓
Remove on Disconnect

Client Mode:
Connect to Peer → Socket.connect()
    ↓
Create PeerConnection
    ↓
Add to Client List
    ↓
Send Greeting
    ↓
Start Listening
    ↓
Exchange Messages
```

## Threading Model

### 1. Thread Types

**Main Thread (EDT):**

- UI rendering
- Event handling
- User interactions

**Server Accept Thread:**

- Accepts incoming connections
- Creates peer connections
- Managed by ExecutorService

**Peer Connection Threads:**

- One per connected peer
- Continuous message reading
- Blocks on ObjectInputStream.readObject()

**UI Update Thread:**

- SwingUtilities.invokeLater()
- Safe UI updates from network threads
- Queue-based execution

### 2. Thread Safety

**Synchronized Methods:**

- PeerConnection.sendMessage()
- PeerConnection.sendFile()

**Thread-Safe Collections:**

- CopyOnWriteArrayList for peer connections
- Concurrent access without explicit locking

**EDT Safety:**

- All UI updates use invokeLater()
- Network callbacks dispatch to EDT

## Security Considerations

### 1. Current Implementation

**Authentication:**

- Basic username/password
- In-memory storage (not persistent)
- Plain text passwords (demo only)

**Network Security:**

- Unencrypted socket communication
- No authentication between peers
- Trust-based file sharing

### 2. Production Recommendations

**Should Implement:**

1. **Encryption:**

   - SSL/TLS for socket communication
   - End-to-end message encryption
   - Encrypted file transfers

2. **Authentication:**

   - Hashed passwords (bcrypt, scrypt)
   - Persistent user database
   - Session tokens
   - Peer verification

3. **Authorization:**

   - File sharing permissions
   - User blocking/reporting
   - Admin controls

4. **Input Validation:**
   - Sanitize all user input
   - Validate file types
   - Size limits
   - SQL injection prevention

## Performance Optimization

### 1. Current Optimizations

**Stream Management:**

```java
out.reset(); // Prevents ObjectOutputStream memory leak
```

**Thread Pooling:**

```java
ExecutorService threadPool = Executors.newCachedThreadPool();
```

**Efficient Collections:**

```java
CopyOnWriteArrayList<PeerConnection> connections;
```

### 2. Potential Improvements

**Large File Handling:**

- Chunked file transfers
- Progress indicators
- Compression before transfer
- Resume capability

**Message Optimization:**

- Message queuing
- Batch sending
- Compression for large text
- Binary protocol instead of serialization

**Connection Management:**

- Connection pooling
- Keep-alive mechanisms
- Automatic reconnection
- Connection timeout handling

## Testing Guide

### 1. Local Testing

**Single Machine:**

1. Run multiple instances
2. Use localhost (127.0.0.1)
3. Different ports per instance
4. Test all features

**Commands:**

```bash
# Terminal 1 (User 1)
java -cp "bin;lib/*" main.StudyConnectMain

# Terminal 2 (User 2)
java -cp "bin;lib/*" main.StudyConnectMain
```

### 2. Network Testing

**Same LAN:**

1. Find IP with `ipconfig`
2. Start server on one machine
3. Connect from another
4. Test file transfers

**Different Networks:**

1. Configure port forwarding
2. Use public IP
3. Test connection
4. Verify firewall rules

### 3. Test Scenarios

**Basic Functionality:**

- [ ] User registration
- [ ] User login
- [ ] Server start/stop
- [ ] Peer connection
- [ ] Send text message
- [ ] Receive text message
- [ ] Send file
- [ ] Receive file
- [ ] Disconnect
- [ ] Logout

**Edge Cases:**

- [ ] Invalid IP address
- [ ] Invalid port
- [ ] Connection timeout
- [ ] Large file transfer
- [ ] Multiple simultaneous connections
- [ ] Network interruption
- [ ] Server crash recovery

**Performance:**

- [ ] 10 concurrent connections
- [ ] 100 messages/second
- [ ] File > 100MB
- [ ] Long running session (1+ hour)

## Extension Points

### 1. Feature Additions

**Easy:**

- Message history persistence
- User profiles with avatars
- Typing indicators
- Read receipts
- Emojis and rich text

**Medium:**

- Group chat rooms
- Voice messages
- Screen sharing
- Whiteboard collaboration
- Calendar integration

**Advanced:**

- Video/audio calls
- End-to-end encryption
- Distributed hash table (DHT)
- NAT traversal
- Mobile app

### 2. Code Extension

**Adding New Message Type:**

```java
// 1. Add to enum
public enum MessageType {
    // ... existing
    NEW_TYPE
}

// 2. Handle in MessageHandler
@Override
public void onMessageReceived(Message message, PeerConnection connection) {
    if (message.getType() == MessageType.NEW_TYPE) {
        // Handle new type
    }
}

// 3. Send from UI
Message msg = new Message(user, "all", content, MessageType.NEW_TYPE);
client.sendMessage(msg);
```

**Adding New UI Panel:**

```java
// 1. Create panel class
public class NewPanel extends JPanel {
    public NewPanel() {
        initComponents();
    }
}

// 2. Add to dashboard
JSplitPane splitPane = new JSplitPane();
splitPane.setRightComponent(new NewPanel());
```

## Build & Deployment

### 1. Maven Build

```bash
# Clean and compile
mvn clean compile

# Run tests
mvn test

# Package JAR
mvn package

# Install to local repo
mvn install
```

### 2. Manual Build

```bash
# Compile
javac -cp "lib/*" -d bin src/main/**/*.java

# Create JAR
jar cvfm StudyConnect.jar MANIFEST.MF -C bin .

# Run
java -cp "StudyConnect.jar;lib/*" main.StudyConnectMain
```

### 3. Distribution

**Create Release:**

1. Build JAR file
2. Include lib folder with dependencies
3. Add README and USER_GUIDE
4. Create run scripts for each OS
5. Package as ZIP

**Package Structure:**

```
StudyConnect-1.0.0/
├── StudyConnect.jar
├── lib/
│   ├── flatlaf-3.2.5.jar
│   └── flatlaf-intellij-themes-3.2.5.jar
├── README.md
├── USER_GUIDE.md
├── run.bat (Windows)
└── run.sh (Linux/Mac)
```

## API Reference

### NetworkUtil

```java
public class NetworkUtil {
    // Get local IP address
    public static String getLocalIPAddress()

    // Validate IP format
    public static boolean isValidIPAddress(String ip)

    // Validate port range
    public static boolean isValidPort(int port)
}
```

### FileUtil

```java
public class FileUtil {
    // Read file to byte array
    public static byte[] readFileToBytes(File file) throws IOException

    // Write byte array to file
    public static void writeBytesToFile(byte[] data, File file) throws IOException

    // Get file extension
    public static String getFileExtension(String fileName)

    // Format file size
    public static String formatFileSize(long size)
}
```

### MessageHandler Interface

```java
public interface MessageHandler {
    void onMessageReceived(Message message, PeerConnection connection);
    void onFileReceived(FileTransfer fileTransfer, PeerConnection connection);
    void onServerStatus(String status);
}
```

## Troubleshooting Development Issues

### Compilation Errors

**Package does not exist:**

- Ensure FlatLaf JARs in classpath
- Check package declarations
- Verify import statements

**Cannot find symbol:**

- Check method signatures
- Verify class visibility
- Import required classes

### Runtime Errors

**ClassNotFoundException:**

- Add JARs to runtime classpath
- Check JAR file integrity
- Verify main class name

**SocketException:**

- Check port availability
- Verify firewall settings
- Ensure network connectivity

**Serialization Issues:**

- Implement Serializable
- Add serialVersionUID
- Check object compatibility

---

**Version:** 1.0.0  
**Last Updated:** November 2025  
**Maintainer:** Network Programming Course
