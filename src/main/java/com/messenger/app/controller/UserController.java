package com.messenger.app.controller;

import com.messenger.app.model.User;
import com.messenger.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    private final UserService userService;
    
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser(@AuthenticationPrincipal OAuth2User principal) {
        String email = principal.getAttribute("email");
        Optional<User> userOpt = userService.findByEmail(email);
        if (userOpt.isEmpty()) {
            // Erstelle neuen Benutzer wenn er nicht existiert
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setName(principal.getAttribute("name"));
            newUser.setGoogleId(principal.getAttribute("id").toString());
            newUser.setProfilePictureUrl(principal.getAttribute("picture"));
            newUser = userService.saveUser(newUser);
            return ResponseEntity.ok(newUser);
        }
        return ResponseEntity.ok(userOpt.get());
    }
    
    @GetMapping("/online")
    public ResponseEntity<List<User>> getOnlineUsers() {
        List<User> onlineUsers = userService.findOnlineUsers();
        return ResponseEntity.ok(onlineUsers);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUsers(@RequestParam String query) {
        List<User> users = userService.searchUsers(query);
        return ResponseEntity.ok(users);
    }
    
    @PostMapping("/status")
    public ResponseEntity<Void> updateOnlineStatus(
            @AuthenticationPrincipal OAuth2User principal,
            @RequestParam boolean isOnline) {
        
        String email = principal.getAttribute("email");
        Optional<User> userOpt = userService.findByEmail(email);
        if (userOpt.isPresent()) {
            userService.setUserOnlineStatus(userOpt.get().getId(), isOnline);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
