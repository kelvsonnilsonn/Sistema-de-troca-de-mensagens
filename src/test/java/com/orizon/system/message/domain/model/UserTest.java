package com.orizon.system.message.domain.model;

import com.orizon.system.message.domain.valueobjects.Password;
import com.orizon.system.message.domain.valueobjects.Username;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testToString() {
        User user = new User();
        user.setId(20L);
        user.setUsername(new Username("tes"));
        user.setPassword(new Password("testPassword"));

        String result = user.toString();

        System.out.println("=== Teste de toString() ===");
        System.out.println(result);

        assertTrue(result.contains("tes"));
        assertTrue(result.contains("20"));
        assertFalse(result.contains("testPassword"));
    }
}