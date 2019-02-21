package com.delicate.controller;

import com.delicate.pojo.User;
import com.delicate.service.UserService;
import com.delicate.utils.JSONResult;
import com.delicate.utils.MD5Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterLoginController {
    @Autowired
    private UserService userService;

    @PostMapping("/regist")
    public JSONResult register(@RequestBody User user) throws Exception {
        if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
            return JSONResult.errorMsg("Username and password cannot be blank.");
        }

        if (!userService.isUsernameExists(user.getUsername())) {
            user.setNickname(user.getUsername());
            user.setPassword(MD5Utils.getMD5Str(user.getPassword()));
            user.setFansCount(0);
            user.setFollowCount(0);
            user.setReceivedLikeCount(0);
            userService.saveUser(user);
        } else {
            return JSONResult.errorMsg("Sorry, this username has been taken.");
        }

        return JSONResult.ok();
    }
}
