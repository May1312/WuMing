package com.test.serviceImpl;

import com.test.bean.PhotoBean;
import com.test.dao.PicDao;
import com.test.service.PicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yhang on 2016/12/29.
 */
@Service
@Transactional
public class PicServiceImpl implements PicService{
    @Autowired
    private PicDao picDao;

    public int savePhotoInfo(PhotoBean pb) {
        //判断执行更新or新增
        int sum = picDao.selectInfoByUserId(pb.getUserId());
        if(sum>0){
           return picDao.updatePhotoInfo(pb);
        }else {
            return picDao.savePhotoInfo(pb);
        }
    }

    @Override
    public String findPhotoInfoByUserId(String userId) {
        return picDao.findPhotoInfoByUserId(userId);
    }

    @Override
    public PhotoBean getPhotoBeanByUserId(String userId) {

        return picDao.getPhotoBeanByUserId(userId);
    }
}
