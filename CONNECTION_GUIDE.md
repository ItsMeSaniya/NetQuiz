# 🔗 StudyConnect Connection Guide

## How Two People Connect and Chat

### Overview

StudyConnect uses a **Peer-to-Peer (P2P)** architecture where:

- One person starts a **SERVER** (host)
- Others connect as **CLIENTS** (peers)
- Once connected, both can send messages and files

---

## 🎯 Scenario 1: Same Computer Testing (Localhost)

### Person A (You) - Running First Instance:

1. **Start the application:**

   ```bash
   java -cp "target/StudyConnect-1.0.0.jar;target/lib/*" main.StudyConnectMain
   ```

2. **Login:**

   - Username: `admin`
   - Password: `admin`
   - Click "Login"

3. **Start Server:**
   - Click "Start Server" button
   - Port: `8888` (default)
   - Your IP will be shown (e.g., `192.168.1.100`)
   - Status: "Server running on port 8888"

### Person B (Friend) - Running Second Instance:

1. **Open NEW terminal and start second instance:**

   ```bash
   java -cp "target/StudyConnect-1.0.0.jar;target/lib/*" main.StudyConnectMain
   ```

2. **Login:**

   - Username: `admin` (same or different)
   - Password: `admin`
   - Click "Login"

3. **Connect to Person A:**

   - Click "Connect to Peer" button
   - Enter IP: `127.0.0.1` (localhost for same computer)
   - Enter Port: `8888`
   - Click "OK"

4. **Connection Established!**
   - You'll see Person A in your peer list
   - Person A will see you in their peer list
   - Start chatting!

---

## 🌐 Scenario 2: Different Computers (Same WiFi Network)

### Person A (Server/Host):

1. **Find Your IP Address:**

   ```bash
   # Windows Command Prompt:
   ipconfig
   # Look for "IPv4 Address" (e.g., 192.168.1.100)

   # Linux/Mac:
   ifconfig
   # or
   ip addr
   ```

2. **Start StudyConnect:**

   ```bash
   java -cp "target/StudyConnect-1.0.0.jar;target/lib/*" main.StudyConnectMain
   ```

3. **Login and Start Server:**
   - Login with `admin` / `admin`
   - Click "Start Server"
   - Port: `8888`
   - **Share your IP address with Person B** (e.g., 192.168.1.100)

### Person B (Client/Peer):

1. **Get Person A's IP and Port:**

   - Ask Person A for their IP (e.g., `192.168.1.100`)
   - Default port is `8888`

2. **Start StudyConnect:**

   ```bash
   java -cp "target/StudyConnect-1.0.0.jar;target/lib/*" main.StudyConnectMain
   ```

3. **Login:**

   - Username: `admin` (or any username)
   - Password: `admin`

4. **Connect to Person A:**

   - Click "Connect to Peer"
   - IP Address: `192.168.1.100` (Person A's IP)
   - Port: `8888`
   - Click "OK"

5. **Connected!**
   - Start messaging
   - Share files

---

## 💬 How Messaging Works

### After Connection:

1. **Person A** types message → sends to Person B
2. **Person B** receives instantly (appears in chat window)
3. **Person B** replies → Person A receives instantly
4. **Real-time bidirectional communication**

### Example Chat Session:

```
Person A: Hi! Can you send me the notes?
Person B: Sure! Let me share the PDF
[Person B sends file]
Person A: Got it, thanks!
```

---

## 📁 How File Sharing Works

### Sending a File:

1. **Person A** clicks "Send File" button
2. Selects file from computer (e.g., `notes.pdf`)
3. File transfer request sent to **Person B**

### Receiving a File:

1. **Person B** gets notification: "notes.pdf (2.5 MB) - Accept?"
2. Clicks "Accept"
3. Chooses where to save the file
4. File downloads instantly
5. Notification: "File received successfully!"

---

## 🔑 Login Credentials

### Default Account:

- **Username:** `admin`
- **Password:** `admin`

### Important Notes:

- ✅ Both people can use the same `admin/admin` credentials
- ✅ The login is separate from the P2P connection
- ✅ Each instance is independent
- ❌ Users are NOT shared between instances (stored in memory only)

### Creating New Users:

Each instance can create its own users:

1. Click "Register" on login screen
2. Enter new username and password
3. User saved to that instance only

---

## 🚀 Quick Start Commands

### Run on Windows (Git Bash):

**Terminal 1 (Server):**

```bash
cd /c/Users/ItsMeSaniya/Desktop/TestNet
java -cp "target/StudyConnect-1.0.0.jar;target/lib/*" main.StudyConnectMain
# Login → Start Server on port 8888
```

**Terminal 2 (Client):**

```bash
cd /c/Users/ItsMeSaniya/Desktop/TestNet
java -cp "target/StudyConnect-1.0.0.jar;target/lib/*" main.StudyConnectMain
# Login → Connect to Peer: 127.0.0.1:8888
```

### Run on Windows (CMD):

**CMD Window 1:**

```cmd
cd C:\Users\ItsMeSaniya\Desktop\TestNet
run.bat
```

**CMD Window 2:**

```cmd
cd C:\Users\ItsMeSaniya\Desktop\TestNet
run.bat
```

---

## 🔧 Technical Details

### Architecture:

```
┌─────────────────┐                    ┌─────────────────┐
│   Person A      │                    │   Person B      │
│   (Server)      │                    │   (Client)      │
├─────────────────┤                    ├─────────────────┤
│ IP: 192.168.1.10│◄──────────────────►│ Connects to:    │
│ Port: 8888      │    TCP Socket      │ 192.168.1.10:888│
│                 │                    │                 │
│ Sends: Messages │                    │ Sends: Messages │
│        Files    │                    │        Files    │
└─────────────────┘                    └─────────────────┘
```

### Connection Process:

1. **Server starts:** `ServerSocket` binds to port 8888
2. **Server listens:** Waits for incoming connections
3. **Client connects:** Creates `Socket` to server's IP:Port
4. **Handshake:** Exchange connection info
5. **Bidirectional streams:** Both can send/receive
6. **PeerConnection objects:** Manage communication

### Data Transfer:

- **Messages:** Serialized `Message` objects via `ObjectOutputStream`
- **Files:** `FileTransfer` object with byte array chunks
- **Threading:** Each peer connection runs in separate thread
- **Thread Pool:** `ExecutorService` manages concurrent connections

---

## 🐛 Troubleshooting

### "Cannot connect to peer"

**Check:**

- ✅ Server is running (clicked "Start Server")
- ✅ Correct IP address (not 0.0.0.0)
- ✅ Correct port number (8888)
- ✅ Same network (WiFi)
- ✅ Firewall allows Java connections

**Fix:**

```bash
# Windows Firewall:
Control Panel → Firewall → Allow app
# Add Java to allowed apps
```

### "Connection refused"

**Reasons:**

- ❌ Server not started yet
- ❌ Wrong port number
- ❌ Firewall blocking

**Solution:**

- Start server FIRST
- Then connect from client
- Use correct port

### "File transfer failed"

**Common issues:**

- File too large (memory limits)
- Connection interrupted
- Invalid save location

**Fix:**

- Try smaller files first
- Ensure stable connection
- Check write permissions

---

## 📊 Example Session

### Complete Walkthrough:

**👤 Alice (Server) - Computer A:**

```
1. Open StudyConnect
2. Login: admin/admin
3. Click "Start Server"
4. See IP: 192.168.1.105
5. Tell Bob: "Connect to 192.168.1.105:8888"
6. Wait for Bob to connect
7. See "Bob connected" notification
8. Type: "Hi Bob!"
9. Send file: homework.pdf
```

**👤 Bob (Client) - Computer B:**

```
1. Open StudyConnect
2. Login: admin/admin
3. Click "Connect to Peer"
4. Enter IP: 192.168.1.105
5. Enter Port: 8888
6. Click OK
7. See "Connected to Alice"
8. Receive message: "Hi Bob!"
9. Reply: "Thanks for the file!"
10. Accept file: homework.pdf
11. Save to: Downloads folder
```

---

## 🎓 Educational Value

### Network Concepts Demonstrated:

- ✅ **TCP/IP Sockets:** Client-server model
- ✅ **Port Binding:** Server listens on specific port
- ✅ **IP Addressing:** Finding and connecting to peers
- ✅ **Multi-threading:** Concurrent connections
- ✅ **Object Serialization:** Data transmission
- ✅ **Event-driven Architecture:** Async messaging
- ✅ **Error Handling:** Network exceptions

---

## 🎯 Summary

### Key Points:

1. **One person starts SERVER** (host)
2. **Others connect as CLIENTS** (peers)
3. **Use admin/admin** to login (default)
4. **Server shares IP:PORT** with peers
5. **Clients connect** using that IP:PORT
6. **Real-time chat** and file sharing enabled
7. **Both can send/receive** (bidirectional)

### Remember:

- 🔑 Login is local (each instance independent)
- 🌐 Connection is network-based (IP + Port)
- 💬 Messages are instant (TCP sockets)
- 📁 Files transfer directly (peer-to-peer)
- 🔄 Multiple peers can connect (thread pool)

---

**Happy Connecting! 🎉**
