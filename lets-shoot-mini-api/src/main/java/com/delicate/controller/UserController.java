package com.delicate.controller;

import com.delicate.pojo.User;
import com.delicate.pojo.vo.UserVO;
import com.delicate.service.UserService;
import com.delicate.utils.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@Api(value = "User Transactions Related", tags = {"User transactions related controller"})
@RequestMapping(path = "/user")
public class UserController extends BasicController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "Query User Info", notes = "Interface for querying user info for profile page")
    @ApiImplicitParam(name = "userId", value = "User's ID", required = true, dataType = "String",
            paramType = "query")
    @PostMapping("/query")
    public JSONResult query(String userId) {
        if (userId.isBlank()) {
            return JSONResult.errorMsg("用户 ID 不能为空");
        }

        User user = userService.queryUserInfo(userId);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);

        return JSONResult.ok(userVO);
    }

    @ApiOperation(value = "Upload Avatar", notes = "Interface for user avatar uploading")
    @ApiImplicitParam(name = "userId", value = "The user's id that is uploading", required = true, dataType = "String",
            paramType = "query")
    @PostMapping("/uploadAvatar")
    public JSONResult uploadAvatar(String userId, @RequestParam("file") MultipartFile[] files) {
        if (StringUtils.isBlank(userId)) {
            return JSONResult.errorMsg("用户 ID 不能为空");
        }

        String fileRootPath = "/Users/barryallen/Desktop/Development/IDEA/lets-shoot/data";
        String fileRelativePath = "/" + userId + "/avatar";
        if (files != null && files.length != 0) {
            FileOutputStream fos = null;
            InputStream is = null;
            try {
                String fileName = files[0].getOriginalFilename();
                if (StringUtils.isNotBlank(fileName)) {
                    String fileFinalPath = fileRootPath + fileRelativePath + "/" + fileName;
                    fileRelativePath += ("/" + fileName);
                    File file = new File(fileFinalPath);
                    if (file.getParentFile() == null || !file.getParentFile().isDirectory()) {
                        file.getParentFile().mkdirs();
                    }
                    fos = new FileOutputStream(file);
                    is = files[0].getInputStream();
                    IOUtils.copy(is, fos);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return JSONResult.errorMsg("上传出错");
            } finally {
                if (fos != null) {
                    try {
                        fos.flush();
                        fos.close();
                        if (is != null) {
                            is.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        User user = new User();
        user.setId(userId);
        user.setFaceImage(fileRelativePath);
        userService.updateUserInfo(user);

        return JSONResult.ok(fileRelativePath);
    }
}
