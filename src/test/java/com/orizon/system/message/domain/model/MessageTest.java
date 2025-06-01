package com.orizon.system.message.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    @Test
    void testToString() {
        Message message = new Message();
        message.setId(10L);
        message.setContent("Test content");
        message.setSender(new User("testUser", "testPassword"));
        message.setReceiver(new User("receiverUser", "testReceiverPassword"));

        String result = message.toString();

        System.out.println("=== Teste de toString() ===");
        System.out.println(result);

        assertTrue(result.contains("Test content"));
        assertTrue(result.contains("10"));
        assertTrue(result.contains("testUser"));
        assertTrue(result.contains("receiverUser"));
    }
}