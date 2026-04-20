package com.example.aitueventsbackend.services;

import com.example.aitueventsbackend.exceptions.UserAlreadyExistException;
import com.example.aitueventsbackend.exceptions.UserNotFoundException;
import com.example.aitueventsbackend.model.Role;
import com.example.aitueventsbackend.model.User;
import com.example.aitueventsbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User create(Long telegramId, String username, String firstName, String lastName) {

        if (userRepository.existsByTelegramId(telegramId)) {
            throw new UserAlreadyExistException(telegramId);
        }

        User user = User.createUser(telegramId, username, firstName, lastName);

        return userRepository.save(user);
    }

    public Page<User> getAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User getById(UUID id) {
        return findByIdOrThrow(id);
    }

    public User fullUpdate(UUID id, Long telegramId, String username, String firstName, String lastName, Role role) {
        User user = findByIdOrThrow(id);

        if (userRepository.existsByTelegramIdAndIdNot(telegramId, id)) {
            throw new UserAlreadyExistException(telegramId);
        }

        user.setTelegramId(telegramId);
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRole(role);

        return userRepository.save(user);
    }

    public User partialUpdate(UUID id, Long telegramId, String username, String firstName, String lastName, Role role) {
        User user = findByIdOrThrow(id);

        if (telegramId != null
                && userRepository.existsByTelegramIdAndIdNot(telegramId, id)) {
            throw new UserAlreadyExistException(telegramId);
        }

        if (telegramId != null) {
            user.setTelegramId(telegramId);
        }
        if (username != null) {
            user.setUsername(username);
        }
        if (firstName != null) {
            user.setFirstName(firstName);
        }
        if (lastName != null) {
            user.setLastName(lastName);
        }
        if (role != null) {
            user.setRole(role);
        }

        return userRepository.save(user);
    }

    public void delete(UUID id) {
        userRepository.delete(findByIdOrThrow(id));
    }

    private User findByIdOrThrow(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}
