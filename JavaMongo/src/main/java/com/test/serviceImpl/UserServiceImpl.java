package com.test.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.test.bean.User;
import com.test.dao.UserDao;
import com.test.service.RedisService;
import com.test.service.UserService;
import com.test.util.MD5Utils;
import com.test.util.UserThreadLocal;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private static final Log logger = LogFactory.getLog(UserServiceImpl.class);
    public String login(User user) {
        User user2 = userDao.findUserByName(user.getName());
        if (user2 != null){
            if (MD5Utils.md5(user.getPassword()).equalsIgnoreCase(user2.getPassword())) {
                //save user infomation
                UserThreadLocal.set(user2);
                //
                String ticket = user.getName();
                String s = JSON.toJSONString(user);
                log4j.info("执行redis");
                redisService.set(ticket, s, 60 * 60);
                return ticket;
            } else {
                logger.info("执行生成ticket异常");
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
}
