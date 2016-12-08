package com.test.interceptor;

import com.test.bean.User;
import com.test.service.UserService;
import com.test.util.CookieUtils;
import com.test.util.UserThreadLocal;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yhang on 2016/12/6.
 */
public class UserInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String ticket = CookieUtils.getCookieValue(request, "hang", true);
        //判断用户是否登陆
        if(StringUtils.isNotBlank(ticket)){
           // (ticket);
            User user =  userService.findUserCacheByTicket(ticket);
            if(user==null) {
                //跳转登陆界面
                System.out.println("跳转到login界面");
                response.sendRedirect("/view/login.jsp");
                return false;
            }
            UserThreadLocal.set(user);
            return true;
        }
        System.out.println("跳转到login界面");
        response.sendRedirect("/view/login.jsp");
        return false;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
