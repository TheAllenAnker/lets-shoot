package com.delicate.controller;

import com.delicate.pojo.User;
import com.delicate.service.UserService;
import com.delicate.utils.JSONResult;
import com.delicate.utils.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "User Register/Login", tags = {"Register/Login Controller"})
public class RegisterLoginController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "User Register", notes = "Interface for user registering")
    @PostMapping("/register")
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

        user.setPassword("");
        return JSONResult.ok(user);
    }

    @ApiOperation(value = "User Login", notes = "Interface for user login")
    @PostMapping("/login")
    public JSONResult login(@RequestBody User user) throws Exception {
        if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
            return JSONResult.errorMsg("用户名或密码不能为空");
        }

        if (userService.isUsernameExists(user.getUsername())) {
            User foundUser = userService.findUserByUsername(user.getUsername());
            if (MD5Utils.getMD5Str(user.getPassword()).equals(foundUser.getPassword())) {
                foundUser.setPassword("");
                return JSONResult.ok(foundUser);
            }
        }

        return JSONResult.errorMsg("用户名或密码不正确");
    }
}
