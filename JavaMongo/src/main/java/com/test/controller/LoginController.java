package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by yhang on 2016/12/9.
 */
@RequestMapping("/login")
@Controller
public class LoginController {
    @RequestMapping(value = "",method= RequestMethod.GET)
    public String login(){
        return "login";
    }
}
