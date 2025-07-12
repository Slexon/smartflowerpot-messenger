# Copilot Instructions

<!-- Use this file to provide workspace-specific custom instructions to Copilot. For more details, visit https://code.visualstudio.com/docs/copilot/copilot-customization#_use-a-githubcopilotinstructionsmd-file -->

This is a Java Spring Boot messenger application project with the following technologies:

## Tech Stack
- **Backend**: Spring Boot 3.2.0 with Java 17
- **Security**: Spring Security with OAuth2 (Google Authentication)
- **Database**: JPA/Hibernate with H2 (development) and MySQL (production)
- **Real-time Communication**: WebSocket with STOMP protocol
- **Frontend**: Thymeleaf templates with Bootstrap 5
- **Build Tool**: Gradle

## Project Structure
- **Models**: User and Message entities with JPA annotations
- **Repositories**: Spring Data JPA repositories for database operations
- **Services**: Business logic for user and message management
- **Controllers**: REST API endpoints and WebSocket message handlers
- **Security**: OAuth2 configuration for Google authentication
- **Frontend**: Responsive web interface with real-time messaging

## Key Features
- Google OAuth2 authentication
- Real-time messaging with WebSocket
- User online status tracking
- Message delivery and read receipts
- User search functionality
- Responsive design similar to WhatsApp

## Development Guidelines
- Follow Spring Boot best practices
- Use Lombok for reducing boilerplate code
- Implement proper error handling
- Use DTOs for API responses when needed
- Maintain clean separation of concerns
- Write meaningful commit messages
