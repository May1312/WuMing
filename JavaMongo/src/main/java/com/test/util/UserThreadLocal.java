package com.test.util;

/**
 * Created by yhang on 2016/12/6.
 */
public class UserThreadLocal {

    // 声明一个ThreadLocal
    private static ThreadLocal userThreadLocal = new ThreadLocal();

    // 把用户信息放到ThreadLocal
    public static void set(Object obj) {
        userThreadLocal.set(obj);
    }

    // 从ThreadLocal中获取用户信息
    public static Object get() {
        return userThreadLocal.get();
    }

}
