# Java Messenger App Setup

## 🛠️ Installation und Setup

### Schritt 1: Java Development Kit (JDK) installieren

Da nur Java Runtime Environment (JRE) installiert ist, müssen Sie zuerst ein JDK installieren:

#### Option A: OpenJDK (Empfohlen)
1. Laden Sie OpenJDK 11 herunter: https://adoptium.net/
2. Installieren Sie das JDK
3. Setzen Sie die JAVA_HOME Umgebungsvariable:
   ```powershell
   $env:JAVA_HOME = "C:\Program Files\Eclipse Adoptium\jdk-11.0.XX-hotspot"
   $env:PATH = "$env:JAVA_HOME\bin;$env:PATH"
   ```

#### Option B: Oracle JDK
1. Laden Sie Oracle JDK 11 herunter: https://www.oracle.com/java/technologies/downloads/
2. Installieren Sie das JDK
3. Setzen Sie die JAVA_HOME Umgebungsvariable

### Schritt 2: Google OAuth2 konfigurieren

1. Gehen Sie zur [Google Cloud Console](https://console.cloud.google.com/)
2. Erstellen Sie ein neues Projekt oder wählen Sie ein existierendes aus
3. Aktivieren Sie die "Google+ API"
4. Erstellen Sie OAuth2-Credentials:
   - Typ: Web application
   - Authorized redirect URIs: `http://localhost:8080/login/oauth2/code/google`
5. Kopieren Sie Client ID und Client Secret

### Schritt 3: Anwendung konfigurieren

Bearbeiten Sie `src/main/resources/application.properties`:

```properties
# Ersetzen Sie mit Ihren Google OAuth2 Credentials
spring.security.oauth2.client.registration.google.client-id=IHRE_GOOGLE_CLIENT_ID
spring.security.oauth2.client.registration.google.client-secret=IHR_GOOGLE_CLIENT_SECRET
```

### Schritt 4: Anwendung bauen und starten

```powershell
# Projekt bauen
.\gradlew.bat build

# Anwendung starten
.\gradlew.bat bootRun
```

Die Anwendung ist dann verfügbar unter: http://localhost:8080

## 🚨 Aktuelle Systemkonfiguration

Ihr System hat derzeit:
- Java Runtime Environment (JRE) 1.8.0_451
- **PROBLEM**: Für die Kompilierung wird Java Development Kit (JDK) benötigt

### JDK Installation prüfen

Nach der JDK-Installation können Sie diese Befehle verwenden:

```powershell
# Java-Version prüfen
java -version

# JDK-Compiler prüfen
javac -version

# JAVA_HOME prüfen
echo $env:JAVA_HOME
```

## 📁 Vollständiges Projekt

Das Projekt enthält:

### Backend (Spring Boot 2.7.14)
- ✅ Messenger-Anwendung Hauptklasse
- ✅ User & Message Modelle mit JPA
- ✅ Repository-Schicht
- ✅ Service-Schicht (UserService, MessageService)
- ✅ REST-Controller für API-Endpunkte
- ✅ WebSocket-Controller für Echtzeit-Messaging
- ✅ OAuth2-Sicherheitskonfiguration
- ✅ WebSocket-Konfiguration

### Frontend
- ✅ Startseite mit Google-Login
- ✅ Login-Seite
- ✅ Chat-Interface (WhatsApp-ähnlich)
- ✅ Responsive Design mit Bootstrap 5
- ✅ WebSocket-Integration für Echtzeit-Chat

### Konfiguration
- ✅ Gradle Build-Konfiguration
- ✅ H2-Datenbank für Entwicklung
- ✅ Application Properties
- ✅ VS Code Tasks

## 🎯 Features

- **Google OAuth2 Authentifizierung**: Sichere Anmeldung
- **Echtzeit-Messaging**: WebSocket-basierte Kommunikation
- **Online-Status**: Zeigt an, wer online ist
- **Benutzersuche**: Finde andere Benutzer
- **Nachrichtenverlauf**: Persistente Speicherung
- **Responsive Design**: Desktop und Mobile

## 🔧 Fehlerbehebung

### Build-Fehler "Could not find tools.jar"
**Ursache**: Nur JRE installiert, kein JDK
**Lösung**: Installieren Sie JDK (siehe Schritt 1)

### OAuth2-Fehler
**Ursache**: Falsche Client ID/Secret
**Lösung**: Überprüfen Sie die Google Cloud Console Konfiguration

### Port-Konflikte
**Ursache**: Port 8080 bereits in Verwendung
**Lösung**: Ändern Sie den Port in `application.properties`:
```properties
server.port=8081
```

## 📞 Nach erfolgreicher JDK-Installation

Nach der JDK-Installation können Sie:

1. Das Projekt bauen: `.\gradlew.bat build`
2. Die Anwendung starten: `.\gradlew.bat bootRun`
3. H2-Konsole öffnen: http://localhost:8080/h2-console
4. Chat verwenden: http://localhost:8080

---

**Das Projekt ist vollständig vorbereitet und wartet nur auf die JDK-Installation!**
