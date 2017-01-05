package com.test.controller;

import com.test.service.WeiBoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by yhang on 2017/1/5.
 */
@Controller
@RequestMapping("/weibo")
public class WeiBoController {
    @Autowired
    private WeiBoService weiBoService;

    @RequestMapping(value = "/checkUid",method = RequestMethod.POST)
    public ResponseEntity checkUid(@RequestParam("uid") String uid){
        String flag = weiBoService.checkUid(uid)+"";
        return ResponseEntity.ok(flag);
    }
}
