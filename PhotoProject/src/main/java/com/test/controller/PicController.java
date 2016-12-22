package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by yhang on 2016/12/21.
 */
@Controller
@RequestMapping("/photo")
public class PicController {
    @RequestMapping(value = "/upload",method = {RequestMethod.POST,RequestMethod.GET})
    public void uploadphoto(@RequestParam("img") MultipartFile upload){
        System.out.println("--------------");
        System.out.println(upload);

    }
}
