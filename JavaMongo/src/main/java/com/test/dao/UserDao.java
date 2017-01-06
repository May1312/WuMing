package com.test.dao;

import com.test.bean.User;
import com.test.util.MongoUtil;
import org.mongodb.morphia.query.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by yhang on 2016/12/6.
 */
@Repository
public class UserDao {

    public User findUserByName(String name){

        Query<User> query = MongoUtil.getDataStore().createQuery(User.class);
        query.field("name").equal(name);
       if (query.asList().size()>0){
           return query.asList().get(0);
        }
        return null;
    }

    public User findUserByUserId(String userId) {
        Query<User> query = MongoUtil.getDataStore().createQuery(User.class);
        query.field("userId").equal(userId);
        if (query.asList().size()>0){
            return query.asList().get(0);
        }
        return null;
    }
}
