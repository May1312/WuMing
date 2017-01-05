package com.test.dao;

import org.springframework.stereotype.Repository;

/**
 * Created by yhang on 2017/1/5.
 */
@Repository
public interface WeiBoDao {
    int checkUid(String uid);
}
