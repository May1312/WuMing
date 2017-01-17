package com.test.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by yhang on 2017/1/17.
 */
public class UserWeibo implements Serializable {

    private static final long serialVersionUID = -8627563439775382058L;

    private int id;

    private String uid;

    private String userId;

    private int status = 0;

    private Date createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
