package com.test.dao;

import com.test.bean.UserWeibo;
import org.springframework.stereotype.Repository;

/**
 * Created by yhang on 2017/1/5.
 */
@Repository
public interface WeiBoDao {
    public int checkUid(String uid);
    public String findUserIdByUid(String uid);
    public void saveUid(String uid,String userId);
    public int saveUidAndUserId(UserWeibo uw);
}
