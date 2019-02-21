package com.delicate.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "users_reports")
public class UsersReports {
    @Id
    private String id;

    @Column(name = "deal_user_id")
    private String dealUserId;

    @Column(name = "deal_video_id")
    private String dealVideoId;

    private String title;

    private String content;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "created_time")
    private Date createdTime;

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
     * @return deal_user_id
     */
    public String getDealUserId() {
        return dealUserId;
    }

    /**
     * @param dealUserId
     */
    public void setDealUserId(String dealUserId) {
        this.dealUserId = dealUserId;
    }

    /**
     * @return deal_video_id
     */
    public String getDealVideoId() {
        return dealVideoId;
    }

    /**
     * @param dealVideoId
     */
    public void setDealVideoId(String dealVideoId) {
        this.dealVideoId = dealVideoId;
    }

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return user_id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return created_time
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * @param createdTime
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}