package com.orizon.system.message.repositories;

import com.orizon.system.message.domain.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
