package com.test.dao;

import com.mongodb.*;
import com.test.bean.User;
import com.test.util.DateUtils;
import com.test.util.MongoUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.mongodb.morphia.AdvancedDatastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

@Repository
public class MongoDao {
   // @Autowired
   // private MongoTemplate mongoTemplate;

    public void add(User user) throws Exception {
        user.setRegistTime(DateUtils.convert2String2(new Date()));
        AdvancedDatastore dataStore = MongoUtil.getDataStore();
        dataStore.insert(user);
    }
    public List<User> queryUser(int currentPage, int pageSize){
        Query<User> query = MongoUtil.getDataStore().createQuery(User.class);
        query.offset(currentPage);
        query.limit(pageSize);
        return query.asList();
    }
    public int queryUserCount(){
        Query<User> query = MongoUtil.getDataStore().createQuery(User.class);
        return (int)query.countAll();
    }
    /**
     * spring管理的mongodb连接
     *
     * @param user
     * @throws Exception
     */
    /*public void add2(User user) throws Exception {
        DBCollection dbCollection = mongoTemplate.getCollection("User");
        BasicDBObject doc1 = new BasicDBObject();
        doc1.put("userId", user.getUserId());
        doc1.put("name", user.getName());
        doc1.put("age", user.getAge());
        doc1.put("sex", user.getSex());
        dbCollection.insert(doc1);
    }*/

    /*public void query(User user) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        DBCollection dbCollection = mongoTemplate.getCollection("User");
        DBCursor cursor = dbCollection.find();
        while (cursor.hasNext()) {
            User user2 = dbObject2Bean(cursor.next(), user);
            System.out.println(user2);
        }
    }*/

    public static <T> T dbObject2Bean(DBObject dbObject, T bean)
            throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {
        if (bean == null) {
            return null;
        }
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            String varName = field.getName();
            Object object = dbObject.get(varName);
            if (object != null) {
                BeanUtils.setProperty(bean, varName, object);
            }
        }
        return bean;
    }

    public int queryUserByUser(User user) {
        Query<User> query = MongoUtil.getDataStore().createQuery(User.class);
        query.field("userId").equal(user.getUserId());
        return (int)query.countAll();
    }

    public void updateUser(User user) {
        Query<User> query = MongoUtil.getDataStore().createQuery(User.class);
        query.field("userId").equal(user.getUserId());
        System.out.print(user.getUserId());
        UpdateOperations<User> ops = MongoUtil.getDataStore().createUpdateOperations(User.class);
        ops.set("name", user.getName());
        ops.set("age", user.getAge());
        ops.set("sex", user.getSex());
        MongoUtil.getDataStore().update(query, ops);
    }

    public void remove(String userId) {
        Query<User> query = MongoUtil.getDataStore().createQuery(User.class);
        query.field("userId").equal(userId);
        MongoUtil.getDataStore().delete(query);
    }

    public List<User> checkname(String name) {
        Query<User> query = MongoUtil.getDataStore().createQuery(User.class);
        query.field("name").equal(name);
        return query.asList();
    }

    public int checkname2(String name) {
        Query<User> query = MongoUtil.getDataStore().createQuery(User.class);
        query.field("name").equal(name);
        return (int)query.countAll();
    }
}
