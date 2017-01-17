package com.test.serviceImpl;

import com.test.bean.UserWeibo;
import com.test.dao.WeiBoDao;
import com.test.service.WeiBoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yhang on 2017/1/5.
 */
@Service
public class WeiBOServiceImpl implements WeiBoService {
    @Autowired
    private WeiBoDao weiBoDao;
    @Override
    public int checkUid(String uid) {
        int i = weiBoDao.checkUid(uid);
        return i;
    }

    @Override
    public String findUserIdByUid(String uid) {
        String userId = weiBoDao.findUserIdByUid(uid);
        return userId;
    }

    @Override
    public int saveUidAndUserId(String uid, String userId) {
        UserWeibo uw = new UserWeibo();
        uw.setUserId(userId);
        uw.setUid(uid);
        System.out.println(uw.getId());
        weiBoDao.saveUidAndUserId(uw);
        System.out.println(uw.getId());
        return uw.getId();
    }
}
