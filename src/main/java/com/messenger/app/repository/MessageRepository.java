package com.messenger.app.repository;

import com.messenger.app.model.Message;
import com.messenger.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    
    @Query("SELECT m FROM Message m WHERE (m.sender = :user1 AND m.receiver = :user2) OR (m.sender = :user2 AND m.receiver = :user1) ORDER BY m.sentAt ASC")
    List<Message> findConversationBetweenUsers(@Param("user1") User user1, @Param("user2") User user2);
    
    @Query("SELECT m FROM Message m WHERE m.receiver = :user AND m.isRead = false ORDER BY m.sentAt DESC")
    List<Message> findUnreadMessagesForUser(@Param("user") User user);
    
    @Query("SELECT DISTINCT CASE WHEN m.sender = :user THEN m.receiver ELSE m.sender END FROM Message m WHERE m.sender = :user OR m.receiver = :user ORDER BY MAX(m.sentAt) DESC")
    List<User> findConversationPartners(@Param("user") User user);
    
    @Query("SELECT m FROM Message m WHERE (m.sender = :user OR m.receiver = :user) AND m = (SELECT m2 FROM Message m2 WHERE (m2.sender = :user AND m2.receiver = CASE WHEN m.sender = :user THEN m.receiver ELSE m.sender END) OR (m2.receiver = :user AND m2.sender = CASE WHEN m.sender = :user THEN m.receiver ELSE m.sender END) ORDER BY m2.sentAt DESC LIMIT 1)")
    List<Message> findLastMessagesForUser(@Param("user") User user);
}
