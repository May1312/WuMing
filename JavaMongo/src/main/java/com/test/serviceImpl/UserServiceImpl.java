package com.test.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.test.bean.HttpResult;
import com.test.bean.User;
import com.test.dao.UserDao;
import com.test.service.HttpClientUtils;
import com.test.service.RedisService;
import com.test.service.UserService;
import com.test.util.MD5Utils;
import com.test.util.UserThreadLocal;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yhang on 2016/12/6.
 */
@Service
public class UserServiceImpl implements UserService {
    static Logger log4j = Logger.getLogger(UserServiceImpl.class.getClass());
    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisService redisService;

    @Autowired
    private HttpClientUtils httpClientUtils;

    @Value("${WeiBo_UID_URL}")
    private String WeiBo_UID_URL;

    public String login(User user) {
        User user2 = userDao.findUserByName(user.getName());
        if (user2 != null){
            if (MD5Utils.md5(user.getPassword()).equalsIgnoreCase(user2.getPassword())) {
                //save user infomation
                UserThreadLocal.set(user2);
                //
                String ticket = user.getName();
                String s = JSON.toJSONString(user2);
                redisService.set(ticket, s, 60 * 60);
                return ticket;
            } else {
                System.out.println("密码错误 方法");
                return null;
            }
      }
    return null;
    }

    public User findUserCacheByTicket(String ticket) {
        //httpclient wait
        String s = redisService.get(ticket);
        //json---->bean
        User user = JSON.parseObject(s, User.class);
        return user;
    }
    //weibo-login
    public String weiboLogin(String uid) {
        //根据uid查询---->user info
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("uid",uid);
        WeiBo_UID_URL = WeiBo_UID_URL+"/findUserIdByUid";
        HttpResult httpResult = httpClientUtils.doPost(WeiBo_UID_URL, map);
        String userId = httpResult.getBody();
        if(StringUtils.isNotBlank(userId)){
            User user2 = userDao.findUserByUserId(userId);
                    //save user infomation
                    UserThreadLocal.set(user2);
                    //
                    String ticket = user2.getName();
                    String s = JSON.toJSONString(user2);
                    redisService.set(ticket, s, 60 * 60);
                    return ticket;
                } else {
                    System.out.println("密码错误 方法");
                    return null;
                }
        }
}
