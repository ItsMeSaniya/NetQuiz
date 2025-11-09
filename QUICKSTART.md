# StudyConnect - Quick Start Guide

## 🚀 Get Started in 5 Minutes!

### Prerequisites

- ✅ Java 8 or higher installed
- ✅ Internet connection (for downloading dependencies)

### Quick Setup

#### Method 1: Using Maven (Recommended - Easiest!)

```bash
# 1. Navigate to project folder
cd TestNet

# 2. Build (downloads dependencies automatically)
# Windows:
build.bat

# Linux/Mac:
chmod +x build.sh
./build.sh

# 3. Run
# Windows:
run.bat

# Linux/Mac:
./run.sh
```

That's it! The application will launch automatically.

#### Method 2: Manual Build (No Maven)

```bash
# 1. Create lib folder
mkdir lib

# 2. Download dependencies (see DEPENDENCIES.md for links)
# Place these files in lib/:
# - flatlaf-3.2.5.jar
# - flatlaf-intellij-themes-3.2.5.jar

# 3. Compile
# Windows:
compile-manual.bat

# Linux/Mac:
chmod +x compile-manual.sh
./compile-manual.sh

# 4. Run
# Windows:
run-manual.bat

# Linux/Mac:
./run-manual.sh
```

## 🎯 First Time Usage

### Step 1: Login

1. Application opens with login screen
2. Use default credentials:
   - **Username:** `admin`
   - **Password:** `admin`
3. Click **Login**

### Step 2: Start Your Server

1. You'll see your IP address displayed (e.g., 192.168.1.100)
2. Port is set to **8888** (you can change this)
3. Click **Start Server**
4. Status shows "Server started"

### Step 3: Connect with a Friend

**On Your Computer:**

- Server is already running from Step 2
- Share your IP and port with friend

**On Friend's Computer:**

1. Login to StudyConnect
2. Click **Connect to Peer**
3. Enter your IP address (e.g., 192.168.1.100)
4. Enter port: 8888
5. Click **OK**

### Step 4: Start Chatting!

1. Type message in text field
2. Press **Enter** or click **Send**
3. Messages appear in chat area
4. Both can send and receive instantly!

### Step 5: Share Files

1. Click **Send File**
2. Select a file
3. Friend receives notification
4. Friend accepts and saves file

## 📱 Testing on Same Computer

Want to test alone? Run two instances:

```bash
# Terminal 1 (User 1)
java -cp "bin;lib/*" main.StudyConnectMain
# Start server on port 8888

# Terminal 2 (User 2)
java -cp "bin;lib/*" main.StudyConnectMain
# Connect to 127.0.0.1:8888
```

## 🌐 Network Setup

### Same WiFi Network (Easiest)

1. Both computers on same WiFi
2. Use local IP (192.168.x.x)
3. No additional setup needed!

### Different Networks (Internet)

1. Setup port forwarding on router
2. Forward port 8888 to your computer
3. Share public IP with friend
4. Friend connects using public IP

## 🎨 Features Overview

### ✉️ Messaging

- Real-time text chat
- Timestamps on all messages
- Multiple peer support
- System notifications

### 📁 File Sharing

- Any file type supported
- Progress notifications
- Choose save location
- Size display

### 👥 Peer Management

- See all connected peers
- Multiple connections
- Connection status
- Easy disconnect

### 🔒 User Management

- Registration system
- Login/logout
- User profiles
- Session management

## ⚡ Quick Commands Reference

### Build Commands

```bash
mvn clean package          # Maven build
./compile-manual.sh        # Manual compile (Linux/Mac)
compile-manual.bat         # Manual compile (Windows)
```

### Run Commands

```bash
./run.sh                   # Run Maven build (Linux/Mac)
run.bat                    # Run Maven build (Windows)
./run-manual.sh           # Run manual build (Linux/Mac)
run-manual.bat            # Run manual build (Windows)
```

### Network Commands

```bash
ipconfig                   # Get IP (Windows)
ifconfig                   # Get IP (Linux/Mac)
ping <ip>                  # Test connection
telnet <ip> <port>        # Test port
```

## 🐛 Quick Troubleshooting

### Can't Build?

- ✅ Check Java installed: `java -version`
- ✅ Check Maven installed: `mvn -version`
- ✅ Download dependencies manually (see DEPENDENCIES.md)

### Can't Connect?

- ✅ Verify server is started
- ✅ Check IP address is correct
- ✅ Check port number matches
- ✅ Disable firewall temporarily
- ✅ Ping the IP first

### UI Looks Wrong?

- ✅ Check FlatLaf JARs in lib folder
- ✅ Rebuild the project
- ✅ Update Java to latest version

### Port Already in Use?

- ✅ Change port number (try 9999)
- ✅ Close other applications
- ✅ Check Task Manager for Java processes

## 📚 Next Steps

Once you're up and running:

1. **Read USER_GUIDE.md** - Detailed feature guide
2. **Read TECHNICAL_DOC.md** - Architecture and implementation
3. **Customize** - Modify colors, features, UI
4. **Extend** - Add new features (voice, video, etc.)

## 💡 Tips for Best Experience

### Connection Tips

- 🔹 Use wired connection for stability
- 🔹 Close bandwidth-heavy applications
- 🔹 Test with small files first
- 🔹 Keep port number consistent

### Security Tips

- 🔹 Only connect to trusted peers
- 🔹 Verify file sources
- 🔹 Use strong passwords
- 🔹 Avoid sensitive files on public networks

### Performance Tips

- 🔹 Limit simultaneous connections
- 🔹 Close unused chat windows
- 🔹 Clear chat history periodically
- 🔹 Compress large files before sharing

## 🎓 Learning Objectives

This project demonstrates:

- ✅ Socket programming (TCP/IP)
- ✅ Multithreading
- ✅ Object serialization
- ✅ GUI development (Swing)
- ✅ Client-server architecture
- ✅ Peer-to-peer networking
- ✅ File I/O operations
- ✅ Event-driven programming

## 📞 Getting Help

### Documentation

- 📖 README.md - Project overview
- 📖 USER_GUIDE.md - Detailed user manual
- 📖 TECHNICAL_DOC.md - Technical details
- 📖 DEPENDENCIES.md - Library information

### Common Questions

**Q: Can I use on mobile?**
A: Currently desktop only. Mobile version could be future enhancement.

**Q: Is it secure?**
A: Basic implementation for learning. Not recommended for sensitive data.

**Q: Can I modify it?**
A: Yes! It's open for learning. Add features as you like.

**Q: How many peers can connect?**
A: Limited by system resources. Tested with 10+ concurrent connections.

## 🎉 You're Ready!

You now have everything you need to:

- ✅ Build StudyConnect
- ✅ Run it on your computer
- ✅ Connect with peers
- ✅ Share files and chat
- ✅ Understand the code
- ✅ Extend functionality

**Happy Studying with StudyConnect! 🚀📚**

---

_Created for Network Programming Assignment_  
_Version 1.0.0 - November 2025_
