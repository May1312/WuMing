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
       int num = picDao.savePhotoInfo(pb);
        if(num!=0){
            return num;
        }
        return 0;
    }

    @Override
    public void run(int i) {
        System.out.println(i);
    }
}
