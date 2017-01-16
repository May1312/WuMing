package com.test.controller;

import com.alibaba.fastjson.JSON;
import com.test.bean.User;
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
    @RequestMapping(value = "/findUserIdByUid",method = RequestMethod.POST)
    public ResponseEntity findUserIdByUid(@RequestParam("uid") String uid){
        String userId = weiBoService.findUserIdByUid(uid);
        return ResponseEntity.ok(userId);
    }
    @RequestMapping(value = "/saveUid",method = RequestMethod.POST)
    public ResponseEntity saveUid(@RequestParam("uid") String uid,@RequestParam("user") String user){
        User user1 = JSON.parseObject(user, User.class);
        //save uid and user to mysql
        int id = weiBoService.saveUidAndUserId(uid,user1.getUserId());
        return ResponseEntity.ok(id);
    }
}
