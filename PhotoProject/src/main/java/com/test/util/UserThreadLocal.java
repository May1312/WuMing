package com.test.util;

import com.test.bean.User;

/**
 * Created by yhang on 2016/12/6.
 */
public class UserThreadLocal {

    // 声明一个ThreadLocal
    private static ThreadLocal<User> userThreadLocal = new ThreadLocal<User>();

    // 把用户信息放到ThreadLocal
    public static void set(User user) {
        userThreadLocal.set(user);
    }

    // 从ThreadLocal中获取用户信息
    public static User get() {
        return userThreadLocal.get();
    }

}
