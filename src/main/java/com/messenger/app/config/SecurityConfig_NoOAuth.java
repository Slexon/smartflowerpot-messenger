package com.messenger.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Temporäre Security-Konfiguration ohne OAuth2 für lokale Entwicklung
 * Aktivieren Sie diese Konfiguration mit: spring.profiles.active=dev
 */
@Configuration
@EnableWebSecurity
@Profile("dev")
public class SecurityConfig_NoOAuth {
    
    @Bean
    @Primary
    public SecurityFilterChain devSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/**").permitAll() // Alle Anfragen erlauben
            )
            .headers(headers -> headers.frameOptions().disable()); // For H2 console
        
        return http.build();
    }
}
