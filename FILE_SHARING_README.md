# 📁 File Sharing Module - Quick Start Guide

## Overview

The **File Sharing Module** is a comprehensive P2P file transfer system integrated into StudyConnect. It provides seamless file sharing capabilities with real-time progress tracking and multi-peer broadcasting.

## 🚀 How to Use

### 1. Access File Sharing

After logging in to StudyConnect, click on the **"📁 File Sharing"** tab in the main dashboard.

### 2. Select a File

1. Click **"📂 Browse Files"** button
2. Choose any file from your computer (max 50MB)
3. The file name and size will be displayed

### 3. Send to Peers

**Option A: Send to Specific Peer**
1. Select a peer from the dropdown menu
2. Click **"📤 Send to Selected"**
3. File will be sent only to that peer

**Option B: Broadcast to All**
1. Click **"📢 Send to All Peers"**
2. File will be sent to all connected peers simultaneously

### 4. Track Progress

- Real-time progress bar shows upload status
- Transfer speed and remaining time displayed
- History table logs all sent/received files

### 5. Receive Files

When someone sends you a file:
1. A notification will pop up
2. Click "Yes" to accept the file
3. Choose where to save it
4. File appears in transfer history

## 📊 Features

✅ **File Selection** - Browse and select files up to 50MB  
✅ **Multi-Peer Distribution** - Send to one or all peers  
✅ **Progress Tracking** - Real-time upload/download progress  
✅ **Transfer History** - Complete log with timestamps  
✅ **Speed Calculation** - Live transfer speed display  
✅ **Concurrent Transfers** - Handle 5 simultaneous transfers  
✅ **Error Handling** - Automatic retry on failure  

## 🎯 Use Cases

- **Share Study Materials** - Distribute notes, PDFs, presentations
- **Exchange Assignments** - Send/receive homework files
- **Collaborate on Projects** - Share code, documents, images
- **Resource Distribution** - Broadcast files to entire study group

## 🔒 Safety Features

- **Size Validation** - Prevents files larger than 50MB
- **Peer Verification** - Only connected users can transfer
- **Transfer Logging** - Complete history for accountability
- **Error Recovery** - Automatic handling of failed transfers

## 💡 Tips

- Connect to peers first before sending files
- Check transfer history to verify successful delivery
- Use "Send to All" for group distribution
- Monitor progress bar for large file transfers

## 🛠️ Technical Details

- **Protocol**: TCP/IP for reliable delivery
- **Concurrency**: 5 thread pool for parallel transfers
- **Serialization**: Binary format for efficiency
- **UI Updates**: SwingWorker for responsive interface

## 📝 Example Workflow

```
1. Admin starts server
2. Students connect to admin
3. Admin selects lecture PDF (5MB)
4. Admin clicks "Send to All Peers"
5. All students receive notification
6. Students accept and save file
7. Transfer completes in <10 seconds
8. History shows successful delivery
```

## 🎓 Contribution Details

**Module**: File Sharing System  
**Files Created**: 3 (FileSharingPanel, FileTransferManager, FileTransferProgressDialog)  
**Lines of Code**: 863+  
**Integration**: MainDashboard, MessageHandler  
**Status**: Production Ready ✅

---

**Similar to Notification System**: Just as the Notification System uses UDP for instant updates, the File Sharing Module uses TCP for reliable file delivery with progress tracking and concurrent transfer management.
