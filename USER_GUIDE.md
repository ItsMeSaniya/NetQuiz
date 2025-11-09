# StudyConnect - User Guide

## Table of Contents

1. [Getting Started](#getting-started)
2. [Installation](#installation)
3. [Running the Application](#running-the-application)
4. [Features & Usage](#features--usage)
5. [Troubleshooting](#troubleshooting)

## Getting Started

### Prerequisites

- **Java JDK 8 or higher** - [Download](https://www.oracle.com/java/technologies/downloads/)
- **Maven** (recommended) - [Download](https://maven.apache.org/download.cgi)
- **Windows/Linux/Mac** operating system

## Installation

### Option 1: Using Maven (Recommended)

1. **Install Maven** if not already installed
2. **Open Command Prompt/Terminal** in the project directory
3. **Run the build script:**

   ```bash
   # Windows
   build.bat

   # Linux/Mac
   chmod +x build.sh
   ./build.sh
   ```

### Option 2: Manual Compilation

1. **Download FlatLaf libraries:**

   - Visit: https://github.com/JFormDesigner/FlatLaf/releases
   - Download `flatlaf-3.2.5.jar`
   - Download `flatlaf-intellij-themes-3.2.5.jar`

2. **Create lib folder** and place JAR files inside:

   ```
   TestNet/
   ├── lib/
   │   ├── flatlaf-3.2.5.jar
   │   └── flatlaf-intellij-themes-3.2.5.jar
   └── src/
   ```

3. **Run the compilation script:**

   ```bash
   # Windows
   compile-manual.bat

   # Linux/Mac
   chmod +x compile-manual.sh
   ./compile-manual.sh
   ```

## Running the Application

### Using Maven Build

```bash
# Windows
run.bat

# Linux/Mac
./run.sh

# Or manually
java -cp "target/StudyConnect-1.0.0.jar:target/lib/*" main.StudyConnectMain
```

### Using Manual Build

```bash
# Windows
run-manual.bat

# Linux/Mac
./run-manual.sh

# Or manually
java -cp "bin:lib/*" main.StudyConnectMain
```

## Features & Usage

### 1. Login & Registration

**First Time Users:**

1. Launch the application
2. Click "Register" button
3. Enter:
   - Username (unique)
   - Email address
   - Password
4. Click "Create Account"
5. Login with your credentials

**Default Test Account:**

- Username: `admin`
- Password: `admin`

### 2. Starting Your Server

To receive connections from other peers:

1. **Set your port** (default: 8888)

   - Valid range: 1024-65535
   - Make sure the port is not in use

2. **Click "Start Server"**

   - Your IP address will be displayed
   - Share this IP and port with peers

3. **Server Status**
   - Green "Stop Server" button indicates running
   - Status bar shows connection events

### 3. Connecting to Peers

To connect to another user:

1. **Click "Connect to Peer"**
2. **Enter peer information:**
   - IP Address: (e.g., 192.168.1.100)
   - Port: (default: 8888)
3. **Click OK**
4. **Connection established** when successful
   - Peer appears in "Connected Peers" list
   - Chat becomes active

### 4. Sending Messages

**Text Chat:**

1. Type your message in the text field
2. Press Enter or click "Send"
3. Messages appear in chat area
4. Timestamp shows when sent

**Message Format:**

```
[HH:MM:SS] Username: Message content
```

### 5. File Sharing

**Sending Files:**

1. **Click "Send File"** button
2. **Select file** from file chooser
3. **Confirm** - File is sent to all connected peers
4. **Status** shown in chat area

**Receiving Files:**

1. **Popup appears** when file received
2. **Review file details:**
   - Filename
   - File size
   - Sender name
3. **Click Yes** to accept
4. **Choose save location**
5. **File saved** to selected location

**Supported File Types:**

- Documents (PDF, DOC, DOCX, TXT)
- Images (JPG, PNG, GIF)
- Archives (ZIP, RAR)
- Code files (JAVA, PY, JS, etc.)
- Any file type

### 6. Managing Connections

**View Connected Peers:**

- List shows: Username (IP:Port)
- Green indicator = Active connection

**Disconnect from Peer:**

- Select peer in list
- Connection closes gracefully

**Server Management:**

- Stop Server: Closes all incoming connections
- Start Server: Reopens server on same port

### 7. Logging Out

1. **Click "Logout"** button
2. **Confirm** logout action
3. **All connections closed** automatically
4. **Return to login** screen

## Network Configuration

### Finding Your IP Address

**Windows:**

```bash
ipconfig
# Look for IPv4 Address
```

**Linux/Mac:**

```bash
ifconfig
# or
ip addr show
```

### Port Forwarding (Optional)

For connections over the Internet:

1. **Access your router settings**
2. **Find Port Forwarding section**
3. **Forward port 8888** (or your chosen port) to your local IP
4. **Use public IP** for remote connections

### Firewall Configuration

**Windows Firewall:**

1. Open Windows Defender Firewall
2. Click "Allow an app through firewall"
3. Add Java/StudyConnect
4. Allow on both Private and Public networks

**Linux (UFW):**

```bash
sudo ufw allow 8888/tcp
```

## Tips & Best Practices

### Security

- ✓ Use strong passwords
- ✓ Only connect to trusted peers
- ✓ Verify file sources before opening
- ✓ Use private networks when possible

### Performance

- ✓ Close unused connections
- ✓ Limit file sizes (< 50MB recommended)
- ✓ Use wired connection for stability
- ✓ Avoid multiple large file transfers

### Collaboration

- ✓ Share your IP address securely
- ✓ Coordinate port numbers
- ✓ Use descriptive usernames
- ✓ Organize study sessions in advance

## Troubleshooting

### Cannot Start Server

**Error: "Port already in use"**

- Solution: Choose a different port or close other applications

**Error: "Permission denied"**

- Solution: Use port above 1024 or run with admin rights

### Cannot Connect to Peer

**Error: "Connection refused"**

- Check peer's server is running
- Verify IP address is correct
- Check port number matches
- Ensure firewall allows connection

**Error: "Connection timeout"**

- Check network connectivity
- Verify peer is on same network (or port forwarded)
- Try pinging the IP address first

### Messages Not Sending

- Verify connection is active
- Check network stability
- Restart the application
- Reconnect to peer

### Files Not Transferring

- Check file size (very large files may timeout)
- Ensure sufficient disk space
- Verify file permissions
- Try smaller files first

### Application Won't Start

**Error: "Could not find or load main class"**

- Rebuild the application
- Check classpath includes all JAR files
- Verify Java is installed correctly

**Error: "Unsupported class version"**

- Update to Java 8 or higher
- Check Java version: `java -version`

### UI Issues

**Blurry or pixelated text**

- Enable high-DPI scaling in Java
- Update to latest Java version

**Colors/Theme not loading**

- FlatLaf library missing
- Redownload required JAR files

## Advanced Features

### Multiple Peer Connections

You can connect to multiple peers simultaneously:

1. Start your server
2. Connect to multiple peers
3. Messages broadcast to all

### Custom Port Configuration

```java
// In MainDashboard.java, modify default port
portField = new JTextField("9999", 8); // Change from 8888
```

### Network Discovery (Future Feature)

Planned features:

- Automatic peer discovery on LAN
- Peer availability status
- Group chat rooms
- Voice/video calls

## Support & Resources

### Documentation

- README.md - Project overview
- JavaDoc - Code documentation
- Source code - Well commented

### Community

- Report issues on GitHub
- Submit feature requests
- Contribute improvements

### Learning Resources

- Java Socket Programming
- Network Programming basics
- Swing GUI development
- Peer-to-Peer architecture

## Appendix

### Default Credentials

```
Username: admin
Password: admin
```

### Default Configuration

```
Server Port: 8888
Connection Timeout: 5 seconds
Max File Size: Unlimited (memory dependent)
```

### File Locations

```
Source Code: src/
Compiled Classes: bin/ or target/
Libraries: lib/
Documentation: README.md, USER_GUIDE.md
```

---

**Version:** 1.0.0  
**Last Updated:** November 2025  
**Author:** Network Programming Assignment
