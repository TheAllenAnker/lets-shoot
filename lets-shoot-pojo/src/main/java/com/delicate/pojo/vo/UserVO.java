package com.delicate.pojo.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "User Login Type", description = "Entity Type for User Registering")
public class UserVO {
    @ApiModelProperty(hidden = true)
    private String id;

    @ApiModelProperty(hidden = true)
    private String userToken;

    @ApiModelProperty(value = "Username", name = "username", example = "username_example", required = true)
    private String username;

    private String nickname;

    @ApiModelProperty(value = "Password", name = "password", example = "password_example!#$12", required = true)
    @JsonIgnore
    private String password;

    @ApiModelProperty(hidden = true)
    private int fansCount;

    @ApiModelProperty(hidden = true)
    private int followCount;

    @ApiModelProperty(hidden = true)
    private int receivedLikeCount;

    @ApiModelProperty(hidden = true)
    private String faceImage;


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getFansCount() {
        return fansCount;
    }

    public void setFansCount(int fansCount) {
        this.fansCount = fansCount;
    }

    public int getFollowCount() {
        return followCount;
    }

    public void setFollowCount(int followCount) {
        this.followCount = followCount;
    }

    public int getReceivedLikeCount() {
        return receivedLikeCount;
    }

    public void setReceivedLikeCount(int receivedLikeCount) {
        this.receivedLikeCount = receivedLikeCount;
    }

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return faceImage
     */
    public String getFaceImage() {
        return faceImage;
    }

    /**
     * @param faceImage
     */
    public void setFaceImage(String faceImage) {
        this.faceImage = faceImage;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}