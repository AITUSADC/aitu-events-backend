package com.example.aitueventsbackend.services;

import com.example.aitueventsbackend.exceptions.UserAlreadyExistException;
import com.example.aitueventsbackend.exceptions.UserNotFoundException;
import com.example.aitueventsbackend.model.Role;
import com.example.aitueventsbackend.model.User;
import com.example.aitueventsbackend.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    public User create(Long telegramId, String username, String firstName, String lastName) {

        if (userRepo.existsByTelegramId(telegramId)) {
            throw new UserAlreadyExistException(telegramId);
        }

        User user = User.createUser(telegramId, username, firstName, lastName);

        return userRepo.save(user);
    }

    public Page<User> getAll(Pageable pageable) {
        return userRepo.findAll(pageable);
    }

    public User getById(UUID id) {
        return userRepo.findById(id).orElseThrow(() -> new UserNotFoundException());
    }



    public User fullUpdate(UUID id, Long telegramId, String username, String firstName, String lastName, Role role) {
        User user = userRepo.findById(id).orElseThrow(UserNotFoundException::new);

        // check if new telegram id already exist
        if (!user.getTelegramId().equals(telegramId) && userRepo.existsByTelegramId(telegramId)) {
            throw new UserAlreadyExistException(telegramId);
        }

        user.setTelegramId(telegramId);
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRole(role);

        return userRepo.save(user);
    }

    public User partialUpdate(UUID id, Long telegramId, String username, String firstName, String lastName, Role role) {
        User user = userRepo.findById(id).orElseThrow(UserNotFoundException::new);

        // check if new telegram id already exist
        if (telegramId != null
                && !user.getTelegramId().equals(telegramId)
                && userRepo.existsByTelegramId(telegramId)) {

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

        return userRepo.save(user);
    }

    public void delete (UUID id) {
        if (!userRepo.existsById(id)) {
            throw new UserNotFoundException();
        }
        userRepo.deleteById(id);
    }
}
