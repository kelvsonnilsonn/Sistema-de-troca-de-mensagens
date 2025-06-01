package com.orizon.system.message.repositories;

import com.orizon.system.message.domain.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {
    Optional<List<Message>> findByReceiverId(Long receiverId);
    Optional<List<Message>> findBySenderId(Long senderId);
}