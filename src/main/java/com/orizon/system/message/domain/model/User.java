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
}
