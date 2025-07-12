# 🚀 RAILWAY DEPLOYMENT GUIDE - smartflowerpot.com

## ✅ Schritt 1: GitHub Repository (BEREIT!)
Ihr lokaler Git ist bereit! Jetzt müssen Sie es auf GitHub hochladen:

### 📁 GitHub Repository erstellen:
1. Gehen Sie zu: **https://github.com/new**
2. Repository Name: `smartflowerpot-messenger`
3. Description: `WhatsApp-Web style messenger for smartflowerpot.com`
4. **Public** auswählen (für kostenloses Railway)
5. **Create repository** klicken

### 📤 Code hochladen:
Nach der Repository-Erstellung führen Sie diese Befehle aus:

```bash
# Repository URL von GitHub kopieren und hier einfügen:
git remote add origin https://github.com/IHR_USERNAME/smartflowerpot-messenger.git
git branch -M main
git push -u origin main
```

---

## 🚀 Schritt 2: Railway Account & Deployment

### 📝 Railway Account erstellen:
1. Gehen Sie zu: **https://railway.app/**
2. **"Login with GitHub"** klicken
3. GitHub Account verbinden und autorisieren

### 🚀 Projekt erstellen:
1. **"New Project"** klicken
2. **"Deploy from GitHub repo"** auswählen
3. **Repository `smartflowerpot-messenger` auswählen**
4. **Deploy** klicken

### ⚙️ Environment Variables setzen:
**Variables** Tab → **New Variable**:

```
Variable: SPRING_PROFILES_ACTIVE
Value: prod

Variable: GOOGLE_CLIENT_ID  
Value: YOUR_GOOGLE_CLIENT_ID_HERE

Variable: GOOGLE_CLIENT_SECRET
Value: YOUR_GOOGLE_CLIENT_SECRET_HERE

Variable: JWT_SECRET
Value: smartflowerpot_production_secret_2025_secure_key
```

### 🗄️ PostgreSQL Datenbank hinzufügen:
1. **"New"** → **"Database"** → **"Add PostgreSQL"**
2. Database wird automatisch erstellt und `DATABASE_URL` gesetzt

---

## 🌐 Schritt 3: Domain Configuration

### 🔗 Domain zu Railway hinzufügen:
1. **Settings** → **Domains**
2. **"Custom Domain"** klicken
3. **`smartflowerpot.com`** eingeben
4. **"Add"** klicken

### 📡 DNS bei Domain-Provider konfigurieren:
**Bei Ihrem Domain-Provider (wo smartflowerpot.com registriert ist):**

```
Record Type: CNAME
Name: @
Value: [railway-generated-url].railway.app
TTL: 300 (or Auto)

Record Type: CNAME  
Name: www
Value: [railway-generated-url].railway.app
TTL: 300 (or Auto)
```

**Railway zeigt Ihnen die genaue URL an!**

---

## 🔐 Schritt 4: Google OAuth2 Update

### 🔧 Google Cloud Console:
1. Gehen Sie zu: **https://console.cloud.google.com/**
2. **APIs & Services** → **Credentials**
3. Ihre **OAuth 2.0 Client ID** bearbeiten
4. **Authorized redirect URIs** erweitern:

**HINZUFÜGEN:**
```
https://smartflowerpot.com/login/oauth2/code/google
https://www.smartflowerpot.com/login/oauth2/code/google
```

5. **Save** klicken

---

## ✅ Schritt 5: Deployment Testen

### 🚀 Nach erfolgreichem Deployment verfügbar:
- **https://smartflowerpot.com** - Hauptseite
- **https://smartflowerpot.com/login** - Google Login
- **https://smartflowerpot.com/chat** - Chat Interface
- **https://smartflowerpot.com/actuator/health** - Health Check

### 🔍 Deployment Status prüfen:
1. **Railway Dashboard** → **Deployments**
2. **Logs** anzeigen für Debugging
3. **Metrics** für Performance

---

## 🎯 Nächste Schritte:

### ✅ TODO:
1. [ ] GitHub Repository erstellen
2. [ ] Code hochladen (`git push`)
3. [ ] Railway Account erstellen
4. [ ] Railway Deployment starten
5. [ ] Environment Variables setzen
6. [ ] PostgreSQL Database hinzufügen
7. [ ] Domain `smartflowerpot.com` konfigurieren
8. [ ] DNS bei Domain-Provider update
9. [ ] Google OAuth2 URLs erweitern
10. [ ] **LIVE TESTEN auf smartflowerpot.com!** 🎉

---

## 💰 Kosten-Übersicht:
- ✅ **Railway**: 500 Stunden/Monat kostenlos
- ✅ **PostgreSQL**: Kostenlos (1GB)
- ✅ **SSL Zertifikat**: Automatisch kostenlos
- ✅ **Domain**: Falls bereits vorhanden: 0€

**Gesamtkosten: 0€** (bei bestehender Domain)

---

**🚀 BEREIT FÜR DEPLOYMENT!**
**Folgen Sie den Schritten oben und Ihre App läuft in 15 Minuten auf smartflowerpot.com!**
