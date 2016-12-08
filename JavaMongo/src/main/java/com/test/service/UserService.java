package com.test.service;

import com.test.bean.User;

/**
 * Created by yhang on 2016/12/6.
 */
public interface UserService {
    public String login(User user);

    public User findUserCacheByTicket(String ticket);
}
