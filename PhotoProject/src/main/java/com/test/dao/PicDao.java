package com.test.dao;

import com.test.bean.PhotoBean;
import org.springframework.stereotype.Repository;

/**
 * Created by yhang on 2016/12/29.
 */
@Repository
public interface PicDao {
   public int savePhotoInfo(PhotoBean pb);

   public int selectInfoByUserId(String userId);

   public int updatePhotoInfo(PhotoBean pb);

   public String findPhotoInfoByUserId(String userId);
}
