# 🎓 StudyConnect - Peer-to-Peer Study Collaboration Platform

<div align="center">

![Java](https://img.shields.io/badge/Java-8%2B-orange)
![Swing](https://img.shields.io/badge/UI-Swing-blue)
![FlatLaf](https://img.shields.io/badge/Theme-FlatLaf-green)
![License](https://img.shields.io/badge/License-Educational-yellow)

**A modern, feature-rich peer-to-peer networking application for students to collaborate, share study materials, and communicate in real-time.**

[Quick Start](#-quick-start) • [Features](#-features) • [Documentation](#-documentation) • [Screenshots](#-screenshots)

</div>

---

## 📋 Overview

StudyConnect is a **Java Swing-based peer-to-peer networking application** that demonstrates advanced network programming concepts including:

- TCP/IP socket programming
- Multi-threaded server architecture
- Object serialization for data transfer
- Modern GUI design with FlatLaf
- Real-time bidirectional communication

Built as a networking assignment, StudyConnect showcases professional software development practices with clean architecture, comprehensive documentation, and an attractive user interface.

## ✨ Features

### 🔐 Authentication System

- User registration and login
- Secure password handling
- Session management
- Persistent user profiles

### 💬 Real-Time Messaging

- Instant text chat
- Message timestamps
- Multiple peer connections
- System notifications
- Message history

### 📁 File Sharing

- Share any file type
- Progress indicators
- File size display
- Accept/reject transfers
- Choose save location

### 🌐 Peer-to-Peer Networking

- Server/Client architecture
- Direct peer connections
- Multi-threaded handling
- Automatic connection management
- Status monitoring

### 🎨 Modern UI Design

- Clean, intuitive interface
- Material Design inspired
- FlatLaf modern look & feel
- Responsive layout
- Professional color scheme

## 🚀 Quick Start

### Option 1: Maven Build (Recommended)

```bash
# Clone/Download the project
cd TestNet

# Build with Maven (auto-downloads dependencies)
# Windows:
build.bat

# Linux/Mac:
chmod +x build.sh && ./build.sh

# Run the application
# Windows:
run.bat

# Linux/Mac:
./run.sh
```

### Option 2: Manual Build

```bash
# 1. Create lib folder and download dependencies
mkdir lib
# Download from: https://github.com/JFormDesigner/FlatLaf/releases
# - flatlaf-3.2.5.jar
# - flatlaf-intellij-themes-3.2.5.jar

# 2. Compile
# Windows:
compile-manual.bat

# Linux/Mac:
chmod +x compile-manual.sh && ./compile-manual.sh

# 3. Run
# Windows:
run-manual.bat

# Linux/Mac:
./run-manual.sh
```

**First Login:**

- Username: `admin`
- Password: `admin`

📖 **See [QUICKSTART.md](QUICKSTART.md) for detailed setup instructions**

## 🛠️ Technology Stack

| Component         | Technology                    |
| ----------------- | ----------------------------- |
| **Language**      | Java 8+                       |
| **UI Framework**  | Java Swing                    |
| **Look & Feel**   | FlatLaf 3.2.5                 |
| **Networking**    | Java Sockets (TCP/IP)         |
| **Concurrency**   | Java Threads, ExecutorService |
| **Serialization** | Java Object Streams           |
| **Build Tool**    | Maven (optional)              |

## 📁 Project Structure

```
TestNet/
├── src/main/                      # Source code
│   ├── StudyConnectMain.java    # Application entry point
│   ├── model/                    # Data models
│   │   ├── User.java
│   │   ├── Message.java
│   │   └── FileTransfer.java
│   ├── network/                  # Networking layer
│   │   ├── Server.java          # Server socket handler
│   │   ├── Client.java          # Client connections
│   │   ├── PeerConnection.java  # Peer communication
│   │   └── MessageHandler.java  # Event interface
│   ├── ui/                       # User interface
│   │   ├── LoginFrame.java      # Authentication UI
│   │   ├── MainDashboard.java   # Main window
│   │   ├── ChatWindow.java      # Chat interface
│   │   ├── FileSharePanel.java  # File sharing
│   │   └── PeerListPanel.java   # Peer management
│   └── util/                     # Utilities
│       ├── NetworkUtil.java     # Network helpers
│       └── FileUtil.java        # File operations
├── lib/                          # Dependencies (manual build)
├── pom.xml                       # Maven configuration
├── build.bat / build.sh         # Build scripts
├── run.bat / run.sh             # Run scripts
└── *.md                         # Documentation
```

## 📚 Documentation

| Document                             | Description                   |
| ------------------------------------ | ----------------------------- |
| [QUICKSTART.md](QUICKSTART.md)       | Get started in 5 minutes      |
| [USER_GUIDE.md](USER_GUIDE.md)       | Complete user manual          |
| [TECHNICAL_DOC.md](TECHNICAL_DOC.md) | Architecture & implementation |
| [DEPENDENCIES.md](DEPENDENCIES.md)   | Library download instructions |

## 🎯 Key Concepts Demonstrated

### Network Programming

- ✅ TCP socket programming
- ✅ Server-client architecture
- ✅ Peer-to-peer communication
- ✅ Connection management
- ✅ Port configuration

### Multithreading

- ✅ Thread pools (ExecutorService)
- ✅ Concurrent connections
- ✅ Thread-safe collections
- ✅ Synchronized methods
- ✅ EDT (Event Dispatch Thread)

### Object-Oriented Design

- ✅ MVC pattern separation
- ✅ Interface-based design
- ✅ Encapsulation
- ✅ Modular architecture
- ✅ Clean code practices

### GUI Development

- ✅ Swing components
- ✅ Layout managers
- ✅ Event handling
- ✅ Custom renderers
- ✅ Modern UI/UX

## 🖼️ Screenshots

### Login Screen

Modern authentication interface with registration support

### Main Dashboard

Central hub with server controls, peer list, and chat

### File Sharing

Easy drag-and-drop file sharing with progress tracking

## 💻 Usage Examples

### Starting a Server

```java
// Your IP: 192.168.1.100
// Port: 8888
// Click "Start Server"
// Share IP and port with peers
```

### Connecting to Peer

```java
// Click "Connect to Peer"
// Enter IP: 192.168.1.100
// Enter Port: 8888
// Click OK
```

### Sending Message

```java
// Type message
// Press Enter or click Send
// Message delivered instantly
```

### Sharing Files

```java
// Click "Send File"
// Select file
// Peer receives notification
// Peer accepts and saves
```

## 🔧 Configuration

### Default Settings

```properties
Server Port: 8888
Connection Timeout: 5 seconds
Thread Pool: Cached (auto-scaling)
IP Address: Auto-detected
```

### Customization

Modify `MainDashboard.java` to change:

- Default port number
- UI colors and themes
- Connection limits
- Message formats

## 🧪 Testing

### Local Testing (Same Computer)

```bash
# Terminal 1
java -cp "bin;lib/*" main.StudyConnectMain
# Start server on port 8888

# Terminal 2
java -cp "bin;lib/*" main.StudyConnectMain
# Connect to 127.0.0.1:8888
```

### Network Testing (Different Computers)

1. Both on same WiFi
2. Start server on Computer A
3. Note IP address (e.g., 192.168.1.100)
4. Connect from Computer B using that IP

### Features to Test

- [ ] Login/Registration
- [ ] Server start/stop
- [ ] Peer connection
- [ ] Text messaging
- [ ] File sharing
- [ ] Multiple connections
- [ ] Disconnect/reconnect
- [ ] Large file transfers

## 🚧 Known Limitations

- No encryption (educational project)
- In-memory user database (not persistent)
- Limited to TCP/IP networks
- No voice/video support
- Basic authentication

## 🔮 Future Enhancements

Potential improvements:

- 🔒 SSL/TLS encryption
- 💾 Database integration
- 🎤 Voice/video calls
- 📱 Mobile app
- 🌍 NAT traversal
- 👥 Group chat rooms
- 📊 Analytics dashboard
- 🔔 Desktop notifications

## 📝 Assignment Requirements

This project fulfills the following networking concepts:

✅ **Socket Programming**: TCP client-server implementation  
✅ **Multithreading**: Concurrent connection handling  
✅ **Data Transfer**: Object serialization and file sharing  
✅ **GUI Development**: Professional Swing interface  
✅ **Error Handling**: Robust exception management  
✅ **Documentation**: Comprehensive guides and comments

## 🤝 Contributing

This is an educational project. Feel free to:

- Fork and modify
- Add new features
- Improve documentation
- Report issues
- Share with classmates

## 📄 License

This project is created for educational purposes as part of a Network Programming course assignment.

## 👨‍💻 Author

**Network Programming Assignment**  
Course: Computer Networks  
Topic: Peer-to-Peer Communication Systems

## 🙏 Acknowledgments

- **FlatLaf** - Modern Look and Feel for Java Swing
- **Java Swing** - GUI framework
- **Maven** - Dependency management
- Network Programming course materials and references

## 📞 Support

For issues, questions, or suggestions:

1. Check [USER_GUIDE.md](USER_GUIDE.md) for troubleshooting
2. Review [TECHNICAL_DOC.md](TECHNICAL_DOC.md) for implementation details
3. See [QUICKSTART.md](QUICKSTART.md) for setup help

---

<div align="center">

**Built with ❤️ for learning Network Programming**

⭐ Star this repo if you find it helpful! ⭐

</div>
