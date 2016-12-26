package com.test.controller;

import com.test.bean.User;
import com.test.service.MongoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yhang on 2016/12/9.
 */
@RequestMapping("/login")
@Controller
public class LoginController {

    static Logger log4j = Logger.getLogger(LoginController.class.getClass());
    @Autowired
    private MongoService mongoService;
    @RequestMapping(value = "",method= RequestMethod.GET)
    public String login(HttpServletRequest request,Model model){
        String ip = request.getHeader("X-Real-IP");
        System.out.println("您的登陆ip为："+ip);
        log4j.info("ip:"+ip+"访问");
        model.addAttribute("ip",ip);
        return "login";
    }
    @RequestMapping(value = "/checkName",method= RequestMethod.GET)
    public ResponseEntity<Map<Object, Object>> checkName(@RequestParam("name") String name){
        int count = mongoService.checkname(name);
        Map<Object,Object> map = new HashMap<Object, Object>();
        map.put("count",count);
        return ResponseEntity.ok(map);
    }
    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    public ResponseEntity<Map<Object, Object>> receiveDate(@RequestBody User user) {
        log4j.info("注册用户:"+user.getName());
        mongoService.add(user);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("status", "200");
        return ResponseEntity.ok(map);
    }
    @RequestMapping(value = "/ajaxupload",method = {RequestMethod.GET,RequestMethod.POST})
    public void ajaxupload(HttpServletRequest request){
        //post 请求不跨域上传压缩文件成功
        System.out.println(request.getParameter("img").toString());
    }
}
