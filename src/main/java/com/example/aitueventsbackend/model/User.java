package com.example.aitueventsbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "users")
@Data // getters and setters
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, unique = true, name = "telegram_id")
    private Long telegramId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, name = "first_name")
    private String firstName;

    @Column(nullable = false, name = "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Role role;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Instant createdAt;


    public static User createUser (Long telegramId, String username, String firstName, String lastName, Role role) {
        User user = new User();

        user.telegramId = telegramId;
        user.username = username;
        user.firstName = firstName;
        user.lastName = lastName;
        user.role = role != null ? role : Role.USER;

        return user;
    }
}