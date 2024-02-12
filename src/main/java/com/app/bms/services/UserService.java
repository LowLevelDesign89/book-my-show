package com.app.bms.services;

import com.app.bms.models.entity.User;

public interface UserService {
    User createUser(User user);

    User getUserById(Long userId);
}
