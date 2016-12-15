package com.test.controller;

import com.test.bean.User;
import com.test.service.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yhang on 2016/12/9.
 */
@RequestMapping("/login")
@Controller
public class LoginController {

    @Autowired
    private MongoService mongoService;
    @RequestMapping(value = "",method= RequestMethod.GET)
    public String login(){
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
        mongoService.add(user);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("status", "200");
        return ResponseEntity.ok(map);
    }
}
