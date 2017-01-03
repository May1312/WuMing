package com.test.controller;

import com.test.bean.HttpResult;
import com.test.bean.User;
import com.test.service.HttpClientUtils;
import com.test.service.MongoService;
import com.test.util.UserThreadLocal;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Autowired
    private HttpClientUtils httpClientService;

    @Value("${HTTP_PHOTO_URL}")
    private String HTTP_PHOTO_URL;

    @Value("${ALI_PHOTO_URL}")
    private String ALI_PHOTO_URL;

    @RequestMapping(value = "",method= RequestMethod.GET)
    public String login(HttpServletRequest request,Model model){
        String ip = request.getHeader("X-Real-IP");
        System.out.println("您的登陆ip为："+ip);
        log4j.info("ip:"+ip+"访问");
        model.addAttribute("ip",ip);
        return "login";
    }
    @RequestMapping(value = "/checkName",method= RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> checkName(@RequestParam("name") String name){
        System.out.println(HTTP_PHOTO_URL);
        User user = mongoService.checkname(name);
        Map<String,Object> map = new HashMap<String, Object>();
        //查看图片url
        if(user!=null){
            Map<String,Object> map2 = new HashMap<String, Object>();
            map2.put("userId",user.getUserId());
            String url = httpClientService.doGet(HTTP_PHOTO_URL + "showphoto", map2);
            if(StringUtils.isBlank(url)){
                map.put("count",1);
            }else {
                url= StringUtils.substring(url, 12);
               /* url = split[];*/
                url=ALI_PHOTO_URL+url;
                map.put("count",1);
                map.put("url",url);
            }

            return ResponseEntity.ok(map);
        }else {
            map.put("count",0);
            return ResponseEntity.ok(map);
        }

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
    /**
     * 上传用户头像
     */
    @Autowired
    private HttpClientUtils httpclient;
    private static String PHOTO_URL = "http://localhost:81/photo/upload";
    @RequestMapping(value="/photo",method = {RequestMethod.GET,RequestMethod.POST})
    public ResponseEntity photo(HttpServletRequest request){
        String photo = request.getParameter("img").toString();
        String photoname = request.getParameter("photoname");
        Map<String,Object> map = new HashMap<String, Object>();
        if(StringUtils.isNotBlank(photo)){
            map.put("img",photo);
            map.put("photoname",photoname);
            map.put("userid",UserThreadLocal.get().getUserId());
            HttpResult httpResult = httpclient.doPost(PHOTO_URL, map);
            System.out.println(httpResult);
        }
        return null;
    }
}
