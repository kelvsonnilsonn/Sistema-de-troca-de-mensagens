package com.orizon.system.message.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "receiver_id")
    private User receiver;

    private Instant createdAt;

    @Setter
    boolean isRead;

    public Message(String content, User sender, User receiver) {
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
        this.createdAt = Instant.now();
        this.isRead = false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n╔══════════════════════════════════════╗");
        sb.append("\n║              MESSAGE                 ║");
        sb.append("\n╠══════════════════════════════════════╣");
        sb.append(String.format("\n║ %-36s ║", "ID: " + id));
        sb.append(String.format("\n║ %-36s ║", "Content: '" + content + "'"));
        sb.append(String.format("\n║ %-36s ║", "From: " + (sender != null ? sender.getUsername() : "Anônimo")));
        sb.append(String.format("\n║ %-36s ║", "To: " + receiver.getUsername()));
        sb.append(String.format("\n║ %-36s ║", "Sent at: " + createdAt));
        sb.append(String.format("\n║ %-36s ║", "Status: " + (isRead ? "READ" : "UNREAD")));
        sb.append("\n╚══════════════════════════════════════╝");
        return sb.toString();
    }
}
