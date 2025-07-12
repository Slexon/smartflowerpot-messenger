# 🚀 Messenger App - Benutzer finden & Chatten

## ✅ Aktueller Status: KOSTENLOS & FUNKTIONSFÄHIG!

Ihre App verwendet:
- **H2 Database** (kostenlos, läuft im RAM)
- **Google OAuth2** (kostenlos für kleine Nutzung)
- **Lokales Hosting** (keine Cloud-Kosten)

## 📱 SOFORT TESTEN: Mehrere Benutzer simulieren

### Methode 1: Verschiedene Browser (gleicher PC)
1. **Browser 1** (aktuell): http://localhost:8080
2. **Browser 2** (Firefox/Edge): http://localhost:8080
3. **Inkognito-Modus**: http://localhost:8080

→ Mit **verschiedenen Google-Accounts** anmelden!

### Methode 2: Andere Geräte im gleichen WLAN
**Ihre lokale IP: 192.168.178.111**

- **Handy**: http://192.168.178.111:8080
- **Tablet**: http://192.168.178.111:8080
- **Anderer PC**: http://192.168.178.111:8080

## 🔧 Google OAuth2 für Netzwerk erweitern

Für Zugriff von anderen Geräten müssen Sie in der Google Cloud Console zusätzliche Redirect-URIs hinzufügen:

### Schritte:
1. Gehen Sie zu: https://console.cloud.google.com/
2. → APIs & Services → Credentials
3. → Ihre OAuth 2.0 Client ID bearbeiten
4. → "Authorized redirect URIs" erweitern:

**Hinzufügen:**
```
http://192.168.178.111:8080/login/oauth2/code/google
http://localhost:8080/login/oauth2/code/google
```

5. → Speichern

## 🎯 So funktioniert das Benutzer-Finden:

### 1. Automatische Registrierung
- Jeder, der sich mit Google anmeldet, wird automatisch als Benutzer registriert
- Benutzerdaten werden in H2-Datenbank gespeichert

### 2. Benutzer suchen
- Nach der Anmeldung: Suchfeld verwenden
- Suche nach Namen oder E-Mail
- Alle registrierten Benutzer werden gefunden

### 3. Chat starten
- Benutzer anklicken → Chat öffnet sich
- Real-time Messaging via WebSocket
- Nachrichtenhistorie wird gespeichert

## 💡 Erweiterte Optionen (später):

### Option A: Persistent Database (kostenlos)
```properties
# Statt H2 in-memory: H2 file-based
spring.datasource.url=jdbc:h2:file:./data/messenger
```
→ Daten bleiben nach Neustart erhalten

### Option B: MySQL (kostenlos bei Hostern)
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/messenger
spring.datasource.username=root
spring.datasource.password=password
```

### Option C: Cloud-Hosting (kostenlos Tiers)
- **Heroku** (kostenlos für kleine Apps)
- **Railway** (kostenlos bis 500 Stunden/Monat)
- **Render** (kostenlos für statische Apps)

## 🔥 JETZT TESTEN:

1. **Aktueller Browser**: Angemeldet bleiben
2. **Neuer Browser**: http://localhost:8080 → anderen Google-Account
3. **Handy**: http://192.168.178.111:8080 → dritten Google-Account
4. **Benutzer suchen** und chatten!

## 📊 Aktuelle Konfiguration:

- ✅ **Kosten**: 0€ (komplett kostenlos)
- ✅ **Funktionsfähig**: Voll funktionsfähig
- ✅ **Benutzer-Management**: Automatisch via Google OAuth2
- ✅ **Real-time Chat**: WebSocket aktiv
- ✅ **Datenbank**: H2 läuft
- ✅ **Netzwerk-Zugriff**: Aktiviert für 192.168.178.111

**Ihre App ist SOFORT einsatzbereit! 🎉**
