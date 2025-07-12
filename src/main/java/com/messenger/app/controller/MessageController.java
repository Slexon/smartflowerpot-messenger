package com.messenger.app.controller;

import com.messenger.app.model.Message;
import com.messenger.app.model.User;
import com.messenger.app.service.MessageService;
import com.messenger.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    
    private final MessageService messageService;
    private final UserService userService;
    
    @Autowired
    public MessageController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }
    
    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(
            @AuthenticationPrincipal OAuth2User principal,
            @RequestBody Map<String, Object> payload) {
        
        String receiverEmail = (String) payload.get("receiverEmail");
        String content = (String) payload.get("content");
        String messageTypeStr = (String) payload.getOrDefault("messageType", "TEXT");
        
        String senderEmail = principal.getAttribute("email");
        Optional<User> senderOpt = userService.findByEmail(senderEmail);
        Optional<User> receiverOpt = userService.findByEmail(receiverEmail);
        
        if (senderOpt.isEmpty() || receiverOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        
        Message.MessageType messageType = Message.MessageType.valueOf(messageTypeStr);
        Message message = messageService.sendMessage(senderOpt.get(), receiverOpt.get(), content, messageType);
        
        return ResponseEntity.ok(message);
    }
    
    @GetMapping("/conversation/{userEmail}")
    public ResponseEntity<List<Message>> getConversation(
            @AuthenticationPrincipal OAuth2User principal,
            @PathVariable String userEmail) {
        
        String currentUserEmail = principal.getAttribute("email");
        Optional<User> currentUserOpt = userService.findByEmail(currentUserEmail);
        Optional<User> otherUserOpt = userService.findByEmail(userEmail);
        
        if (currentUserOpt.isEmpty() || otherUserOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        
        List<Message> messages = messageService.getConversationBetweenUsers(currentUserOpt.get(), otherUserOpt.get());
        return ResponseEntity.ok(messages);
    }
    
    @GetMapping("/unread")
    public ResponseEntity<List<Message>> getUnreadMessages(@AuthenticationPrincipal OAuth2User principal) {
        String email = principal.getAttribute("email");
        Optional<User> userOpt = userService.findByEmail(email);
        
        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        
        List<Message> unreadMessages = messageService.getUnreadMessagesForUser(userOpt.get());
        return ResponseEntity.ok(unreadMessages);
    }
    
    @PostMapping("/{messageId}/read")
    public ResponseEntity<Void> markAsRead(@PathVariable Long messageId) {
        messageService.markMessageAsRead(messageId);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/conversations")
    public ResponseEntity<List<User>> getConversations(@AuthenticationPrincipal OAuth2User principal) {
        String email = principal.getAttribute("email");
        Optional<User> userOpt = userService.findByEmail(email);
        
        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        
        List<User> conversationPartners = messageService.getConversationPartners(userOpt.get());
        return ResponseEntity.ok(conversationPartners);
    }
}
