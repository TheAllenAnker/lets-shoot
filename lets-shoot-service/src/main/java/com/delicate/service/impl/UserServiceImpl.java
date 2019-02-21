package com.delicate.service.impl;

import com.delicate.dao.UserMapper;
import com.delicate.pojo.User;
import com.delicate.service.UserService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private Sid sid;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean isUsernameExists(String username) {
        User user = new User();
        user.setUsername(username);
        return userMapper.selectOne(user) != null;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void saveUser(User user) {
        user.setId(sid.nextShort());
        userMapper.insert(user);
    }
}
