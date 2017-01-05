package com.test.serviceImpl;

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
        return weiBoDao.checkUid(uid);
    }
}
