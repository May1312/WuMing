package com.test.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.test.bean.User;
import com.test.dao.UserDao;
import com.test.service.RedisService;
import com.test.service.UserService;
import com.test.util.UserThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yhang on 2016/12/6.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisService redisService;
    public String login(User user) {
        User user2 = userDao.findUserByName(user.getName());
        if(user.getPassword().equalsIgnoreCase(user2.getPassword())){
            //save user infomation
            UserThreadLocal.set(user2);
            //
            String ticket = user.getName();
            String s = JSON.toJSONString(user);
            redisService.set(ticket,s,60*60);
            return ticket;
        }else{
            System.out.println("密码错误 方法");
            return null;
        }
    }

    public User findUserCacheByTicket(String ticket) {
        //httpclient wait
        String s = redisService.get(ticket);
        //json---->bean
        User user = JSON.parseObject(s, User.class);
        return user;
    }
}
