# 🚀 Deployment auf smartflowerpot.com

## ✅ Vorbereitung abgeschlossen!

Ihre App ist jetzt bereit für das Deployment auf `smartflowerpot.com`. Folgende Dateien wurden erstellt:

### 📁 Neue Dateien:
- ✅ `application-prod.properties` - Production-Konfiguration
- ✅ `Dockerfile` - Container für Deployment  
- ✅ `railway.toml` - Railway-Konfiguration
- ✅ `build.gradle` - Erweitert um Actuator & PostgreSQL

## 🚀 DEPLOYMENT-OPTIONEN:

### Option 1: Railway (Empfohlen - Einfach & Kostenlos)

#### Schritt 1: GitHub Repository
```bash
git init
git add .
git commit -m "Initial messenger app for smartflowerpot.com"
git remote add origin https://github.com/IHR_USERNAME/messenger-app.git
git push -u origin main
```

#### Schritt 2: Railway Setup
1. Gehen Sie zu: https://railway.app/
2. "New Project" → "Deploy from GitHub repo"
3. Repository auswählen → Automatisches Deployment startet

#### Schritt 3: Domain konfigurieren
1. Railway Dashboard → Settings → Domains
2. "Custom Domain" → `smartflowerpot.com`
3. DNS-Einstellungen bei Ihrem Domain-Provider:
   ```
   Type: CNAME
   Name: @
   Value: [railway-url].railway.app
   ```

#### Schritt 4: Google OAuth2 Update
Gehen Sie zu: https://console.cloud.google.com/
→ APIs & Services → Credentials → Ihre OAuth 2.0 Client ID

**Authorized redirect URIs hinzufügen:**
```
https://smartflowerpot.com/login/oauth2/code/google
https://www.smartflowerpot.com/login/oauth2/code/google
```

### Option 2: Heroku

#### Deployment:
```bash
# Heroku CLI installieren und anmelden
heroku login
heroku create smartflowerpot-messenger
heroku addons:create heroku-postgresql:mini

# Environment Variables setzen
heroku config:set SPRING_PROFILES_ACTIVE=prod
heroku config:set GOOGLE_CLIENT_ID=YOUR_GOOGLE_CLIENT_ID_HERE
heroku config:set GOOGLE_CLIENT_SECRET=YOUR_GOOGLE_CLIENT_SECRET_HERE

# Domain verbinden
heroku domains:add smartflowerpot.com

# Deploy
git push heroku main
```

### Option 3: Render

1. Account erstellen: https://render.com/
2. "New Web Service" → GitHub Repository verbinden  
3. Environment Variables konfigurieren
4. Custom Domain: `smartflowerpot.com`

## 🔧 Lokale Tests vor Deployment:

### Production-Modus lokal testen:
```bash
# PostgreSQL lokal installieren (optional)
# Oder H2 weiter verwenden für lokale Tests

# Application mit prod-Profil starten
./gradlew bootRun -Dspring.profiles.active=prod
```

### Docker lokal testen:
```bash
# Docker Image bauen
docker build -t smartflowerpot-messenger .

# Container starten
docker run -p 8080:8080 \
  -e SPRING_PROFILES_ACTIVE=prod \
  -e GOOGLE_CLIENT_ID=YOUR_GOOGLE_CLIENT_ID_HERE \
  -e GOOGLE_CLIENT_SECRET=YOUR_GOOGLE_CLIENT_SECRET_HERE \
  smartflowerpot-messenger
```

## 📊 Nach dem Deployment verfügbar:

- **Hauptseite**: https://smartflowerpot.com/
- **Health Check**: https://smartflowerpot.com/actuator/health
- **Login**: https://smartflowerpot.com/login
- **Chat**: https://smartflowerpot.com/chat

## 💰 Kosten-Übersicht:

- ✅ **Railway**: 500 Stunden/Monat kostenlos
- ✅ **PostgreSQL**: Kostenlos bei Railway/Heroku
- ✅ **Domain**: Falls smartflowerpot.com bereits vorhanden: 0€
- ✅ **SSL-Zertifikat**: Automatisch kostenlos

## 🎯 Nächste Schritte:

1. **GitHub Repository erstellen**
2. **Railway/Heroku Account erstellen**  
3. **Deployment starten**
4. **Domain konfigurieren**
5. **Google OAuth2 URLs erweitern**
6. **Testen auf smartflowerpot.com**

**Welche Deployment-Option möchten Sie verwenden?**
