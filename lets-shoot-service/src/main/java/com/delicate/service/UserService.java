package com.delicate.service;

import com.delicate.pojo.User;

public interface UserService {
    User findUserByUsername(String username);

    boolean isUsernameExists(String username);

    void saveUser(User user);
}
