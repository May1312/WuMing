package com.test.service;

import com.test.bean.PhotoBean;

/**
 * Created by yhang on 2016/12/29.
 */
public interface PicService {
    public int savePhotoInfo(PhotoBean pb);

    public String findPhotoInfoByUserId(String userId);

    public PhotoBean getPhotoBeanByUserId(String userId);
}
