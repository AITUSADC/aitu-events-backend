package com.example.aitueventsbackend.repository;

import com.example.aitueventsbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByTelegramId(Long telegramId);

    boolean existsByTelegramIdAndIdNot(Long telegramId, UUID id);
}
