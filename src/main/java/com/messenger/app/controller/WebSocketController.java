package com.messenger.app.controller;

import com.messenger.app.model.Message;
import com.messenger.app.model.User;
import com.messenger.app.service.MessageService;
import com.messenger.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;

import java.util.Map;
import java.util.Optional;

@Controller
public class WebSocketController {
    
    private final MessageService messageService;
    private final UserService userService;
    private final SimpMessagingTemplate messagingTemplate;
    
    @Autowired
    public WebSocketController(MessageService messageService, UserService userService, 
                               SimpMessagingTemplate messagingTemplate) {
        this.messageService = messageService;
        this.userService = userService;
        this.messagingTemplate = messagingTemplate;
    }
    
    @MessageMapping("/sendMessage")
    public void sendMessage(Map<String, Object> payload, @AuthenticationPrincipal OAuth2User principal) {
        String receiverEmail = (String) payload.get("receiverEmail");
        String content = (String) payload.get("content");
        String messageTypeStr = (String) payload.getOrDefault("messageType", "TEXT");
        
        String senderEmail = principal.getAttribute("email");
        Optional<User> senderOpt = userService.findByEmail(senderEmail);
        Optional<User> receiverOpt = userService.findByEmail(receiverEmail);
        
        if (senderOpt.isPresent() && receiverOpt.isPresent()) {
            Message.MessageType messageType = Message.MessageType.valueOf(messageTypeStr);
            Message message = messageService.sendMessage(senderOpt.get(), receiverOpt.get(), content, messageType);
            
            // Send to specific user
            messagingTemplate.convertAndSendToUser(
                receiverEmail,
                "/queue/messages",
                message
            );
            
            // Send confirmation to sender
            messagingTemplate.convertAndSendToUser(
                senderEmail,
                "/queue/messages",
                message
            );
        }
    }
    
    @MessageMapping("/userConnected")
    @SendTo("/topic/userStatus")
    public Map<String, Object> userConnected(@AuthenticationPrincipal OAuth2User principal) {
        String email = principal.getAttribute("email");
        Optional<User> userOpt = userService.findByEmail(email);
        if (userOpt.isPresent()) {
            userService.setUserOnlineStatus(userOpt.get().getId(), true);
            return Map.of(
                "email", email,
                "status", "online"
            );
        }
        return null;
    }
    
    @MessageMapping("/userDisconnected")
    @SendTo("/topic/userStatus")
    public Map<String, Object> userDisconnected(@AuthenticationPrincipal OAuth2User principal) {
        String email = principal.getAttribute("email");
        Optional<User> userOpt = userService.findByEmail(email);
        if (userOpt.isPresent()) {
            userService.setUserOnlineStatus(userOpt.get().getId(), false);
            return Map.of(
                "email", email,
                "status", "offline"
            );
        }
        return null;
    }
}
