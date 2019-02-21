package com.delicate.service;

import com.delicate.pojo.User;

public interface UserService {
    boolean isUsernameExists(String username);

    void saveUser(User user);
}
