package com.messenger.app.service;

import com.messenger.app.model.User;
import com.messenger.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    public Optional<User> findByGoogleId(String googleId) {
        return userRepository.findByGoogleId(googleId);
    }
    
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
    
    public List<User> findOnlineUsers() {
        return userRepository.findOnlineUsers();
    }
    
    public List<User> searchUsers(String search) {
        return userRepository.searchUsers(search);
    }
    
    public User createOrUpdateGoogleUser(String email, String name, String googleId, String profilePictureUrl) {
        Optional<User> existingUser = findByGoogleId(googleId);
        
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setName(name);
            user.setEmail(email);
            user.setProfilePictureUrl(profilePictureUrl);
            user.setLastSeen(LocalDateTime.now());
            user.setIsOnline(true);
            return saveUser(user);
        } else {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setName(name);
            newUser.setGoogleId(googleId);
            newUser.setProfilePictureUrl(profilePictureUrl);
            newUser.setIsOnline(true);
            return saveUser(newUser);
        }
    }
    
    public void setUserOnlineStatus(Long userId, boolean isOnline) {
        Optional<User> userOpt = findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setIsOnline(isOnline);
            user.setLastSeen(LocalDateTime.now());
            saveUser(user);
        }
    }
}
