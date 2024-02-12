package com.app.bms.services.impl;

import com.app.bms.exceptions.NotFoundException;
import com.app.bms.models.entity.User;
import com.app.bms.repositories.UserRepository;
import com.app.bms.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        user.setCrearedAt(ZonedDateTime.now());
        user.setLastUpdatedAt(ZonedDateTime.now());
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()) {
            log.error("User with ID: {} doesn't exist in the system", userId);
            throw new NotFoundException("User with ID " + userId + " doesn't exist in the system");
        }
        return userOptional.get();
    }
}
