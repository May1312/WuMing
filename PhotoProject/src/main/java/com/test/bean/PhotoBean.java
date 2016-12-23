package com.test.bean;

import java.io.Serializable;

/**
 * Created by yhang on 2016/12/23.
 */
public class PhotoBean implements Serializable {
    private String photoUrl;
    private String userId;

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
