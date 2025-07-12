package com.messenger.app.service;

import com.messenger.app.model.Message;
import com.messenger.app.model.User;
import com.messenger.app.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    
    private final MessageRepository messageRepository;
    
    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }
    
    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }
    
    public List<Message> getConversationBetweenUsers(User user1, User user2) {
        return messageRepository.findConversationBetweenUsers(user1, user2);
    }
    
    public List<Message> getUnreadMessagesForUser(User user) {
        return messageRepository.findUnreadMessagesForUser(user);
    }
    
    public List<User> getConversationPartners(User user) {
        return messageRepository.findConversationPartners(user);
    }
    
    public Message sendMessage(User sender, User receiver, String content, Message.MessageType messageType) {
        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(content);
        message.setMessageType(messageType);
        message.setIsDelivered(true);
        message.setDeliveredAt(LocalDateTime.now());
        
        return saveMessage(message);
    }
    
    public void markMessageAsRead(Long messageId) {
        Optional<Message> messageOpt = messageRepository.findById(messageId);
        if (messageOpt.isPresent()) {
            Message message = messageOpt.get();
            message.setIsRead(true);
            message.setReadAt(LocalDateTime.now());
            saveMessage(message);
        }
    }
    
    public void markConversationAsRead(User sender, User receiver) {
        List<Message> unreadMessages = messageRepository.findConversationBetweenUsers(sender, receiver)
                .stream()
                .filter(m -> m.getReceiver().equals(receiver) && !m.getIsRead())
                .toList();
        
        unreadMessages.forEach(message -> {
            message.setIsRead(true);
            message.setReadAt(LocalDateTime.now());
            saveMessage(message);
        });
    }
}
