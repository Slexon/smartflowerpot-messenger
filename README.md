# 💬 SmartFlowerPot Messenger

Eine moderne WhatsApp-Web-ähnliche Messenger-Anwendung für smartflowerpot.com

## 🚀 Live Demo
**🌐 [https://smartflowerpot.com](https://smartflowerpot.com)**

## ✨ Features

- 🔐 **Google OAuth2 Authentifizierung**: Sichere Anmeldung mit Google-Konto
- 💬 **Echtzeit-Messaging**: WebSocket-basierte Kommunikation für sofortige Nachrichtenübertragung
- 👥 **Online-Status**: Zeigt an, welche Benutzer online sind
- 📱 **WhatsApp Web Design**: Responsive WhatsApp-ähnliche Benutzeroberfläche
- 🔍 **Benutzersuche**: Finde und starte Unterhaltungen mit anderen Benutzern
- ⚡ **Automatische Benutzererstellung**: Neue Benutzer werden bei OAuth2-Login automatisch registriert
- 🌐 **Multi-Device Support**: Funktioniert auf Desktop und Mobile

## 🛠️ Technologie-Stack

- **Backend**: Spring Boot 3.2.0 mit Java 21
- **Sicherheit**: Spring Security + Google OAuth2
- **Datenbank**: PostgreSQL (Production), H2 (Development)
- **Echtzeit**: WebSocket mit STOMP
- **Frontend**: Thymeleaf + Bootstrap 5 (WhatsApp Web Styling)
- **Deployment**: Railway + Docker
- **Build**: Gradle

## 📋 Voraussetzungen

- Java 17 oder höher
- Google Cloud Console Account für OAuth2-Konfiguration

## ⚙️ Setup

### 1. Google OAuth2 konfigurieren

1. Gehe zur [Google Cloud Console](https://console.cloud.google.com/)
2. Erstelle ein neues Projekt oder wähle ein existierendes aus
3. Aktiviere die "Google+ API"
4. Erstelle OAuth2-Credentials:
   - Typ: Web application
   - Authorized redirect URIs: `http://localhost:8080/login/oauth2/code/google`
5. Kopiere Client ID und Client Secret

### 2. Anwendung konfigurieren

Bearbeite `src/main/resources/application.properties`:

```properties
# Ersetze mit deinen Google OAuth2 Credentials
spring.security.oauth2.client.registration.google.client-id=DEINE_GOOGLE_CLIENT_ID
spring.security.oauth2.client.registration.google.client-secret=DEIN_GOOGLE_CLIENT_SECRET
```

### 3. Anwendung starten

```bash
# Windows
gradlew.bat bootRun

# Linux/Mac
./gradlew bootRun
```

Die Anwendung ist dann verfügbar unter: `http://localhost:8080`

## 🎯 Verwendung

1. **Anmelden**: Klicke auf "Mit Google anmelden"
2. **Benutzer suchen**: Verwende die Suchfunktion, um andere Benutzer zu finden
3. **Chat starten**: Klicke auf einen Benutzer, um eine Unterhaltung zu beginnen
4. **Nachrichten senden**: Schreibe deine Nachricht und drücke Enter oder klicke auf Senden
5. **Status verfolgen**: Sieh Online-Status und Nachrichtenstatus in Echtzeit

## 📁 Projektstruktur

```
src/
├── main/
│   ├── java/com/messenger/app/
│   │   ├── MessengerApplication.java          # Hauptklasse
│   │   ├── config/
│   │   │   ├── SecurityConfig.java            # Security & OAuth2
│   │   │   └── WebSocketConfig.java           # WebSocket-Konfiguration
│   │   ├── controller/
│   │   │   ├── HomeController.java            # Web-Controller
│   │   │   ├── MessageController.java         # REST API für Nachrichten
│   │   │   ├── UserController.java            # REST API für Benutzer
│   │   │   └── WebSocketController.java       # WebSocket-Handler
│   │   ├── model/
│   │   │   ├── User.java                      # Benutzer-Entity
│   │   │   └── Message.java                   # Nachrichten-Entity
│   │   ├── repository/
│   │   │   ├── UserRepository.java            # Benutzer-Repository
│   │   │   └── MessageRepository.java         # Nachrichten-Repository
│   │   └── service/
│   │       ├── UserService.java               # Benutzer-Business-Logic
│   │       └── MessageService.java            # Nachrichten-Business-Logic
│   └── resources/
│       ├── application.properties             # Konfiguration
│       └── templates/                         # HTML-Templates
│           ├── index.html                     # Startseite
│           ├── login.html                     # Login-Seite
│           └── chat.html                      # Chat-Interface
```

## 🔧 API-Endpunkte

### Benutzer
- `GET /api/users/me` - Aktueller Benutzer
- `GET /api/users/online` - Online-Benutzer
- `GET /api/users/search?query=` - Benutzer suchen
- `POST /api/users/status` - Online-Status aktualisieren

### Nachrichten
- `POST /api/messages/send` - Nachricht senden
- `GET /api/messages/conversation/{userEmail}` - Unterhaltung laden
- `GET /api/messages/unread` - Ungelesene Nachrichten
- `POST /api/messages/{messageId}/read` - Als gelesen markieren
- `GET /api/messages/conversations` - Alle Unterhaltungen

### WebSocket
- `/ws` - WebSocket-Verbindung
- `/app/sendMessage` - Nachricht senden
- `/app/userConnected` - Benutzer verbunden
- `/app/userDisconnected` - Benutzer getrennt

## 🗄️ Datenbank

### H2 Console (Entwicklung)
Zugriff auf die H2-Datenbankkonsole: `http://localhost:8080/h2-console`
- URL: `jdbc:h2:mem:messenger`
- Username: `sa`
- Password: `password`

### Produktions-Setup (MySQL)
Für die Produktion ändere in `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/messenger
spring.datasource.username=dein_username
spring.datasource.password=dein_password
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
```

## 🚀 Deployment

### JAR erstellen
```bash
./gradlew build
```

### Docker (optional)
```dockerfile
FROM openjdk:17-jdk-slim
COPY build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
```

## 🤝 Beitrag leisten

1. Fork das Repository
2. Erstelle einen Feature-Branch (`git checkout -b feature/AmazingFeature`)
3. Committe deine Änderungen (`git commit -m 'Add some AmazingFeature'`)
4. Push zum Branch (`git push origin feature/AmazingFeature`)
5. Öffne eine Pull Request

## 📝 Lizenz

Dieses Projekt steht unter der MIT-Lizenz. Siehe die `LICENSE`-Datei für Details.

## 🐛 Problembehebung

### Häufige Probleme

1. **OAuth2-Fehler**: Überprüfe Client ID und Secret in der Konfiguration
2. **WebSocket-Verbindungsfehler**: Stelle sicher, dass der Port 8080 verfügbar ist
3. **Datenbankfehler**: Überprüfe H2-Konsole auf Tabellenstruktur

### Logs anzeigen
```bash
# Debug-Logs aktivieren
logging.level.com.messenger=DEBUG
logging.level.org.springframework.security=DEBUG
```

## 📞 Support

Bei Fragen oder Problemen erstelle bitte ein Issue im Repository.

---

**Entwickelt mit ❤️ und Spring Boot**
