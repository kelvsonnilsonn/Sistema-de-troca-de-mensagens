package com.orizon.system.message.domain.ports.services;

import com.orizon.system.message.domain.model.User;

public interface MessageService {
    void send(User receiver, String message);

    String receive(Long messageId);

    void delete(Long messageId);

    void update(Long messageId, String newContent);

    void findAllMessagesByReceiver(Long receiverId);

    void findAllMessagesBySender(Long senderId);
}
