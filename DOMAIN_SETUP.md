# 🚀 Messenger App auf smartflowerpot.com deployen

## 🌐 Option 1: Railway (Kostenlos + Domain)

### Schritt 1: Railway Setup
1. Gehen Sie zu: https://railway.app/
2. Erstellen Sie kostenloses Konto (GitHub-Login)
3. "New Project" → "Deploy from GitHub repo"
4. Verbinden Sie Ihr GitHub Repository

### Schritt 2: Environment Variables in Railway
```
SPRING_PROFILES_ACTIVE=prod
GOOGLE_CLIENT_ID=YOUR_GOOGLE_CLIENT_ID_HERE
GOOGLE_CLIENT_SECRET=YOUR_GOOGLE_CLIENT_SECRET_HERE
DATABASE_URL=postgresql://user:password@host:port/dbname
```

### Schritt 3: Domain Setup
1. Railway Dashboard → Ihr Projekt → Settings → Domains
2. "Custom Domain" → `smartflowerpot.com` eingeben
3. DNS bei Ihrem Domain-Provider konfigurieren:
   ```
   Type: CNAME
   Name: @ (oder www)
   Value: [railway-generated-url]
   ```

## 🌐 Option 2: Heroku (Kostenlos)

### Schritt 1: Heroku Account
1. https://heroku.com/ → Kostenloses Konto
2. Heroku CLI installieren

### Schritt 2: App erstellen
```bash
heroku create smartflowerpot-messenger
heroku addons:create heroku-postgresql:mini
```

### Schritt 3: Domain verbinden
```bash
heroku domains:add smartflowerpot.com
heroku domains:add www.smartflowerpot.com
```

## 🌐 Option 3: Render (Kostenlos)

### Setup bei Render.com:
1. Kostenloses Konto erstellen
2. "New Web Service" → GitHub Repository verbinden
3. Custom Domain: `smartflowerpot.com`

## 📝 Erforderliche Änderungen für Production:

### 1. application-prod.properties erstellen
```properties
# Production Database (PostgreSQL)
spring.datasource.url=${DATABASE_URL}
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false

# OAuth2 für Production Domain
spring.security.oauth2.client.registration.google.redirect-uri=https://smartflowerpot.com/login/oauth2/code/google
```

### 2. Google OAuth2 Update
Authorized redirect URIs erweitern:
```
https://smartflowerpot.com/login/oauth2/code/google
https://www.smartflowerpot.com/login/oauth2/code/google
```

## 💰 Kosten-Übersicht:

### ✅ KOSTENLOS:
- **Railway**: 500 Stunden/Monat kostenlos (reicht für kleine Apps)
- **Heroku**: Kostenlose Tier verfügbar
- **Render**: Kostenlose statische Sites + Web Services
- **Domain**: Wenn Sie smartflowerpot.com bereits besitzen

### 💵 Falls Domain gekauft werden muss:
- **Namecheap**: ~$10/Jahr für .com
- **Cloudflare**: Domain + DNS kostenlos
- **Google Domains**: ~$12/Jahr

## 🚀 SOFORT-START (Railway):

1. **GitHub Repository erstellen**
2. **Code hochladen**
3. **Railway verbinden**
4. **Domain konfigurieren**
5. **Google OAuth2 Update**

Soll ich Ihnen bei einem der Hosting-Optionen helfen?
