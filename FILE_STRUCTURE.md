# 📁 StudyConnect - Complete File Structure

```
TestNet/
│
├── 📄 README.md                          ⭐ Start here - Project overview
├── 📄 QUICKSTART.md                      🚀 Get started in 5 minutes
├── 📄 USER_GUIDE.md                      📖 Complete user manual
├── 📄 TECHNICAL_DOC.md                   🔧 Architecture & implementation
├── 📄 DEPENDENCIES.md                    📦 Library download guide
├── 📄 IMPLEMENTATION_SUMMARY.md          ✅ Project completion summary
├── 📄 .gitignore                         🔒 Git ignore rules
├── 📄 pom.xml                            🔨 Maven configuration
├── 📄 MANIFEST.MF                        📋 JAR manifest
│
├── 🔧 Build Scripts (Maven)
│   ├── build.bat                         🪟 Build on Windows
│   ├── build.sh                          🐧 Build on Linux/Mac
│   ├── run.bat                           🪟 Run on Windows
│   └── run.sh                            🐧 Run on Linux/Mac
│
├── 🔧 Build Scripts (Manual)
│   ├── compile-manual.bat                🪟 Compile without Maven (Windows)
│   ├── compile-manual.sh                 🐧 Compile without Maven (Linux/Mac)
│   ├── run-manual.bat                    🪟 Run manual build (Windows)
│   ├── run-manual.sh                     🐧 Run manual build (Linux/Mac)
│   ├── download-deps.bat                 📥 Auto-download libs (Windows)
│   └── download-deps.sh                  📥 Auto-download libs (Linux/Mac)
│
├── 📚 lib/                               📦 External libraries (create this)
│   ├── flatlaf-3.2.5.jar                ⬇️  Download this
│   └── flatlaf-intellij-themes-3.2.5.jar⬇️  Download this
│
├── 📂 src/main/                          💻 Source code
│   │
│   ├── 🎯 StudyConnectMain.java         🚪 Application entry point
│   │
│   ├── 📦 model/                        📊 Data models
│   │   ├── User.java                    👤 User entity
│   │   ├── Message.java                 💬 Chat message
│   │   └── FileTransfer.java            📁 File transfer object
│   │
│   ├── 🌐 network/                      🔌 Networking layer
│   │   ├── Server.java                  🖥️  Multi-threaded server
│   │   ├── Client.java                  💻 Client connection
│   │   ├── PeerConnection.java          🤝 Peer handler
│   │   └── MessageHandler.java          📡 Event callback interface
│   │
│   ├── 🎨 ui/                           🖼️  User interface
│   │   ├── LoginFrame.java              🔐 Login & registration
│   │   ├── MainDashboard.java           🏠 Main window
│   │   ├── ChatWindow.java              💬 Chat interface
│   │   ├── FileSharePanel.java          📁 File sharing UI
│   │   └── PeerListPanel.java           👥 Peer list UI
│   │
│   └── 🔧 util/                         🛠️  Utilities
│       ├── NetworkUtil.java             🌐 Network helpers
│       └── FileUtil.java                📄 File operations
│
├── 📂 bin/                              🔨 Compiled classes (auto-created)
│   └── main/
│       ├── StudyConnectMain.class
│       ├── model/*.class
│       ├── network/*.class
│       ├── ui/*.class
│       └── util/*.class
│
└── 📂 target/                           📦 Maven build output (auto-created)
    ├── StudyConnect-1.0.0.jar
    └── lib/
        ├── flatlaf-3.2.5.jar
        └── flatlaf-intellij-themes-3.2.5.jar
```

---

## 📊 File Count Summary

| Category             | Count  | Purpose                    |
| -------------------- | ------ | -------------------------- |
| 📖 Documentation     | 6      | Guides and instructions    |
| 💻 Java Source Files | 13     | Application code           |
| 🔧 Build Scripts     | 8      | Compilation & execution    |
| ⚙️ Configuration     | 3      | Maven, manifest, gitignore |
| 📦 Dependencies      | 2      | UI libraries (FlatLaf)     |
| **Total**            | **32** | **Complete project**       |

---

## 🎯 Essential Files to Know

### For Getting Started

```
📄 QUICKSTART.md          - Read this first!
🔧 build.bat/sh           - Run this to build
🔧 run.bat/sh             - Run this to start
```

### For Understanding

```
📄 README.md              - Project overview
📄 USER_GUIDE.md          - How to use
📄 TECHNICAL_DOC.md       - How it works
```

### For Building Without Maven

```
🔧 download-deps.bat/sh   - Download libraries
🔧 compile-manual.bat/sh  - Compile code
🔧 run-manual.bat/sh      - Run application
```

---

## 🗂️ Package Structure

### Main Package: `main`

```
main/
├── StudyConnectMain       → Entry point
├── model/                 → Data layer
├── network/               → Network layer
├── ui/                    → Presentation layer
└── util/                  → Helper classes
```

### Dependency Flow

```
main.StudyConnectMain
    ↓
main.ui.LoginFrame
    ↓
main.ui.MainDashboard
    ↓
main.network.Server & Client
    ↓
main.network.PeerConnection
    ↓
main.model.Message & FileTransfer
```

---

## 📝 File Descriptions

### 🎯 Entry Point

**StudyConnectMain.java**

- Sets up FlatLaf look and feel
- Launches LoginFrame
- Main method

### 📊 Model Layer (3 files)

**User.java**

- User entity with credentials
- IP and port information
- Online status

**Message.java**

- Chat message model
- Timestamp handling
- Message types (TEXT, FILE, SYSTEM, etc.)

**FileTransfer.java**

- File metadata and content
- Size formatting
- Sender/recipient info

### 🌐 Network Layer (4 files)

**Server.java**

- Multi-threaded ServerSocket
- Accepts incoming connections
- Manages peer connections
- Broadcast capability

**Client.java**

- Initiates connections to peers
- Socket management
- Message sending

**PeerConnection.java**

- Handles bidirectional communication
- Object streams for serialization
- Continuous message reception
- Thread-safe sending

**MessageHandler.java**

- Interface for event callbacks
- Message received events
- File received events
- Status updates

### 🎨 UI Layer (5 files)

**LoginFrame.java**

- Login and registration forms
- Card layout for switching
- User validation
- Modern Material Design UI

**MainDashboard.java**

- Main application window
- Server control panel
- Peer connection management
- Chat interface
- File sharing controls

**ChatWindow.java**

- Dedicated one-on-one chat
- Message history
- Send functionality

**FileSharePanel.java**

- File list display
- Share/download/delete controls
- File management UI

**PeerListPanel.java**

- Connected peers display
- Peer actions (chat, disconnect)
- Custom cell rendering

### 🛠️ Utility Layer (2 files)

**NetworkUtil.java**

- Get local IP address
- Validate IP format
- Validate port range

**FileUtil.java**

- Read file to bytes
- Write bytes to file
- Get file extension
- Format file size

---

## 🔨 Build Output

### Manual Build (`bin/` folder)

```
bin/
└── main/
    ├── StudyConnectMain.class
    ├── model/
    │   ├── User.class
    │   ├── Message.class
    │   └── FileTransfer.class
    ├── network/
    │   ├── Server.class
    │   ├── Client.class
    │   ├── PeerConnection.class
    │   └── MessageHandler.class
    ├── ui/
    │   ├── LoginFrame.class
    │   ├── MainDashboard.class
    │   ├── ChatWindow.class
    │   ├── FileSharePanel.class
    │   └── PeerListPanel.class
    └── util/
        ├── NetworkUtil.class
        └── FileUtil.class
```

### Maven Build (`target/` folder)

```
target/
├── StudyConnect-1.0.0.jar    ← Executable JAR
├── classes/                   ← Compiled classes
├── lib/                       ← Dependencies
│   ├── flatlaf-3.2.5.jar
│   └── flatlaf-intellij-themes-3.2.5.jar
└── maven-*/                   ← Maven metadata
```

---

## 📦 Dependencies Location

### Maven Build

Dependencies automatically downloaded to:

```
target/lib/
├── flatlaf-3.2.5.jar
└── flatlaf-intellij-themes-3.2.5.jar
```

### Manual Build

Place manually in:

```
lib/
├── flatlaf-3.2.5.jar          ← Download from Maven Central
└── flatlaf-intellij-themes-3.2.5.jar  ← Download from Maven Central
```

---

## 🚀 Quick Reference

### Build Commands

```bash
# Maven
build.bat          (Windows)
./build.sh         (Linux/Mac)

# Manual
compile-manual.bat (Windows)
./compile-manual.sh(Linux/Mac)
```

### Run Commands

```bash
# Maven
run.bat            (Windows)
./run.sh           (Linux/Mac)

# Manual
run-manual.bat     (Windows)
./run-manual.sh    (Linux/Mac)
```

### Download Dependencies

```bash
download-deps.bat  (Windows)
./download-deps.sh (Linux/Mac)
```

---

## ✅ Setup Checklist

- [ ] Download/clone project
- [ ] Read QUICKSTART.md
- [ ] Choose build method (Maven or Manual)
- [ ] Download dependencies (if manual)
- [ ] Run build script
- [ ] Run application
- [ ] Login with admin/admin
- [ ] Start server
- [ ] Test connection

---

## 🎓 File Categories by Purpose

### Learning

- 📄 README.md - Project introduction
- 📄 TECHNICAL_DOC.md - Learn architecture
- 💻 All .java files - Study code

### Using

- 📄 QUICKSTART.md - Get started fast
- 📄 USER_GUIDE.md - Learn features
- 🔧 run scripts - Launch app

### Building

- 📄 DEPENDENCIES.md - Setup libraries
- 🔧 build scripts - Compile code
- 📄 pom.xml - Maven config

### Reference

- 📄 IMPLEMENTATION_SUMMARY.md - Complete overview
- 📄 FILE_STRUCTURE.md - This file!

---

**🎉 All 32 files working together to create StudyConnect!**

_Use this guide to navigate the project structure efficiently._
