package com.orizon.system.message.domain.model;

import com.orizon.system.message.domain.valueobjects.Password;
import com.orizon.system.message.domain.valueobjects.Username;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Username username;

    @Embedded
    private Password password;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Message> sentMessages = new ArrayList<>();

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Message> receivedMessages = new ArrayList<>();

    public User(String username, String password) {
        this.username = new Username(username);
        this.password = new Password(password);
    }

    public String getPassword() {
        return password.getPass();
    }

    public String getUsername() {
        return username.getUsername();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n╔════════════════════════════════════════╗");
        sb.append("\n║                USER PROFILE            ║");
        sb.append("\n╠════════════════════════════════════════╣");
        sb.append(String.format("\n║ %-20s: %-16s ║", "ID", id));
        sb.append(String.format("\n║ %-20s: %-16s ║", "Username", username.getUsername()));
        sb.append(String.format("\n║ %-20s: %-15s ║", "Password", "🔒🔒🔒🔒🔒"));
        sb.append(String.format("\n║ %-20s: %-16s ║", "Sent Messages", sentMessages.size()));
        sb.append(String.format("\n║ %-20s: %-16s ║", "Received Messages", receivedMessages.size()));
        sb.append("\n╚════════════════════════════════════════╝");
        return sb.toString();
    }
}
