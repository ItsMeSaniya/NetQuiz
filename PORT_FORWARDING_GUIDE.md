# Port Forwarding Setup Guide

## 🌐 Enable Internet Access for StudyConnect

This guide will help you set up port forwarding so friends on different networks can connect to your StudyConnect server.

---

## Step 1: Find Your Local IP Address

Your application automatically displays your **Local IP** when you start the server (e.g., `192.168.1.100`)

---

## Step 2: Find Your Public IP Address

Your application automatically displays your **Public IP** when you start the server (e.g., `203.94.123.45`)

You can also check it at: https://whatismyipaddress.com

---

## Step 3: Configure Port Forwarding on Your Router

### Access Your Router:
1. Open a web browser
2. Enter one of these addresses (most common):
   - `192.168.1.1`
   - `192.168.0.1`
   - `10.0.0.1`
3. Login with your router credentials (check router label or manual)

### Common Router Login Credentials:
- **Username**: `admin` | **Password**: `admin`
- **Username**: `admin` | **Password**: `password`
- **Username**: `admin` | **Password**: (blank)

### Setup Port Forwarding:
1. Look for sections named:
   - **Port Forwarding**
   - **Virtual Server**
   - **NAT Forwarding**
   - **Applications & Gaming**

2. Create a new port forwarding rule:
   ```
   Service Name:     StudyConnect
   External Port:    8888
   Internal Port:    8888
   Internal IP:      [Your Local IP, e.g., 192.168.1.100]
   Protocol:         TCP
   Status:           Enabled
   ```

3. Save the configuration

---

## Step 4: Configure Windows Firewall

### Allow Incoming Connections on Port 8888:

**Option A: Windows Defender Firewall (GUI)**
1. Press `Win + R`, type `wf.msc`, press Enter
2. Click **Inbound Rules** → **New Rule**
3. Select **Port** → Click **Next**
4. Select **TCP** → Enter port: `8888` → Click **Next**
5. Select **Allow the connection** → Click **Next**
6. Check all profiles (Domain, Private, Public) → Click **Next**
7. Name: `StudyConnect Server` → Click **Finish**

**Option B: PowerShell (Quick Command)**
```powershell
New-NetFirewallRule -DisplayName "StudyConnect Server" -Direction Inbound -LocalPort 8888 -Protocol TCP -Action Allow
```

---

## Step 5: Test Your Connection

### On Your Computer (Server):
1. Login as `admin` / `admin`
2. Click **"Start Server"** on port `8888`
3. Note your **Public IP** displayed (e.g., `203.94.123.45`)

### On Friend's Computer (Client):
1. Login with any username
2. In "Connect to Server" section:
   - **Server IP**: `YOUR_PUBLIC_IP` (e.g., `203.94.123.45`)
   - **Port**: `8888`
3. Click **"Connect to Server"**

---

## Troubleshooting

### ❌ Connection Failed?

**Check Router Port Forwarding:**
- Verify the internal IP matches your computer's local IP
- Make sure the rule is enabled
- Try restarting your router

**Check Windows Firewall:**
- Ensure port 8888 is allowed in both Inbound and Outbound rules
- Temporarily disable firewall to test (remember to re-enable!)

**Check Your ISP:**
- Some ISPs block incoming connections
- Contact your ISP if port forwarding doesn't work
- Consider using a VPN service as an alternative

**Dynamic IP Address:**
- Your public IP may change when your router restarts
- Consider using a Dynamic DNS service (e.g., No-IP, DuckDNS)
- Or share the new public IP with friends each time

---

## Router-Specific Guides

### Popular Router Brands:

**TP-Link:**
1. Go to: Advanced → NAT Forwarding → Virtual Servers
2. Click **Add**
3. Enter port and IP details

**Netgear:**
1. Go to: Advanced → Advanced Setup → Port Forwarding
2. Click **Add Custom Service**
3. Enter port and IP details

**Linksys:**
1. Go to: Applications & Gaming → Single Port Forwarding
2. Enter port and IP details

**D-Link:**
1. Go to: Advanced → Port Forwarding
2. Click **Add**
3. Enter port and IP details

**ASUS:**
1. Go to: WAN → Virtual Server / Port Forwarding
2. Enable port forwarding
3. Add rule with port and IP details

---

## Security Recommendations

⚠️ **Important Security Tips:**

1. ✅ Only share your public IP with trusted friends
2. ✅ Change default admin password in the application
3. ✅ Stop the server when not in use
4. ✅ Monitor connected users regularly
5. ✅ Consider using SSL/TLS encryption for production

---

## Alternative Solutions

If port forwarding doesn't work:

### Option 1: Hamachi VPN (Easiest)
- Download: https://www.vpn.net
- Both users join the same network
- Use Hamachi IP to connect

### Option 2: Radmin VPN (Free)
- Download: https://www.radmin-vpn.com
- Create/join a network
- Use Radmin IP to connect

### Option 3: Cloud Deployment
- Deploy to AWS, DigitalOcean, or Azure
- Get a permanent public IP
- Professional and always available

---

## Connection Summary

| Network Type | Server IP to Share | Who Can Connect |
|--------------|-------------------|-----------------|
| **Local Network** | Local IP (192.168.x.x) | Same WiFi/Router only |
| **Internet** | Public IP (your WAN IP) | Anyone (with port forwarding) |
| **VPN (Hamachi)** | Hamachi IP | VPN network members |

---

## Quick Reference

```
Local Network:     192.168.1.100:8888
Internet:          203.94.123.45:8888 (example)
Port to Forward:   8888
Protocol:          TCP
```

---

**Need Help?**
- Check your router's manual for specific instructions
- Search: "Port forwarding [Your Router Model]"
- Video tutorials available on YouTube

**Last Updated:** November 2025
