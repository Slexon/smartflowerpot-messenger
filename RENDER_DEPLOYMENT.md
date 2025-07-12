# Render Deployment Guide

## Render.com Setup für smartflowerpot.com

### Warum Render?
- ✅ **Kostenlos:** 750 Stunden/Monat
- ✅ **Automatisches Deployment:** GitHub Integration
- ✅ **PostgreSQL:** Kostenlose Datenbank inklusive
- ✅ **Custom Domain:** smartflowerpot.com Support
- ✅ **SSL:** Automatisches HTTPS
- ✅ **Keine Schlafzeit:** Besser als Heroku

### Deployment Steps

#### 1. Render Account erstellen
1. Gehe zu [render.com](https://render.com)
2. Sign up mit GitHub Account
3. Verbinde dein GitHub Repository: `Slexon/smartflowerpot-messenger`

#### 2. Web Service erstellen
1. **New → Web Service**
2. **Repository:** `smartflowerpot-messenger`
3. **Branch:** `main`
4. **Root Directory:** (leer lassen)
5. **Runtime:** `Docker`
6. **Build Command:** (automatisch erkannt)
7. **Start Command:** (automatisch erkannt)

#### 3. Environment Variables
```
SPRING_PROFILES_ACTIVE=prod
GOOGLE_CLIENT_ID=your_google_client_id
GOOGLE_CLIENT_SECRET=your_google_client_secret
JWT_SECRET=smartflowerpot_production_secret_2025
PORT=8080
```

#### 4. PostgreSQL Database
1. **New → PostgreSQL**
2. **Name:** `smartflowerpot-db`
3. **Free Plan**
4. Verbinde mit Web Service

#### 5. Custom Domain
1. **Settings → Custom Domains**
2. **Add Domain:** `smartflowerpot.com`
3. **DNS Setup:** CNAME von smartflowerpot.com zu Render URL

### OAuth2 Redirect URI
Nach Deployment die Render-URL in Google Cloud Console eintragen:
- `https://smartflowerpot.com/login/oauth2/code/google`

### Production Database URL
Render generiert automatisch `DATABASE_URL` Environment Variable für PostgreSQL.

## Vorteile vs Railway
- ✅ Einfachere Konfiguration
- ✅ Bessere Dokumentation
- ✅ Kostenloses PostgreSQL
- ✅ Keine komplizierte TOML-Konfiguration
- ✅ Web-Interface für alle Einstellungen
