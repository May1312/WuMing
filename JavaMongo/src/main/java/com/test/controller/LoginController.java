package com.test.controller;

import com.test.bean.HttpResult;
import com.test.bean.User;
import com.test.service.HttpClientUtils;
import com.test.service.MongoService;
import com.test.service.UserService;
import com.test.util.CookieUtil;
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
import weibo4j.Account;
import weibo4j.Oauth;
import weibo4j.http.AccessToken;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONException;
import weibo4j.org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
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

    @Autowired
    private UserService userService;

    @Value("${HTTP_PHOTO_URL}")
    private String HTTP_PHOTO_URL;

    @Value("${ALI_PHOTO_URL}")
    private String ALI_PHOTO_URL;

    @Value("${WeiBo_UID_URL}")
    private String WeiBo_UID_URL;

    static String uid;
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
            log4j.info("HTTP_PHOTO_URL:"+HTTP_PHOTO_URL);
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
    @RequestMapping(value = "/weibo",method = RequestMethod.GET)
    public void GotoWeiBo(HttpServletRequest request,HttpServletResponse response){
        //触发应用授权
        try {
                PrintWriter writer = response.getWriter();
                Oauth oauth = new Oauth();
            /*writer.print("<script>"+"window.location.href='" + oauth.authorize("code") + "'<script>");*/
            writer.print(oauth.authorize("code"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*@RequestMapping(value = "/weibo",method = RequestMethod.GET)
    public void GotoWeiBo(){
        //触发应用授权
        try {
            Oauth oauth = new Oauth();
            BareBonesBrowserLaunch.openURL(oauth.authorize("code"));

        } catch (WeiboException e) {
            e.printStackTrace();
        }
    }*/
    @RequestMapping(value = "/weibo/code",method = RequestMethod.GET)
    public String getCode(@RequestParam("code") String code, HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            //获取accesstoken 对象
            Oauth oauth = new Oauth();
            AccessToken accessToken = oauth.getAccessTokenByCode(code);
            String access_token = accessToken.getAccessToken();
            //获取uid
            Account am = new Account(access_token);
            JSONObject uidObject = am.getUid();
            uid = uidObject.getString("uid");
            System.out.println("亲uid："+uid);
            map.put("uid",uid);
            HttpResult result = httpClientService.doPost(WeiBo_UID_URL+"checkUid", map);
            System.out.println(result);
            if(result.getBody().equalsIgnoreCase("0")){
                //没有绑定，跳转到绑定界面
                    return "bind_weibo";
            }else{
                //直接登陆
                String ticket = userService.weiboLogin(uid);

                if (StringUtils.isNotBlank(ticket)) {
                    log4j.info("设置浏览器ticket："+ticket);
                    //CookieUtils.setCookie(request, response, "hang", ticket, 60 * 60 * 24 * 1, true);
                    CookieUtil.addCookie("hang", ticket, true, request, response);
                    try {
                        //重定向可以  转发不行
                        return "weibopage";
                       // request.getRequestDispatcher("/mongo/showpage").forward(request, response);
                        /*map.put("msg","request success");
                        map.put("status",200);
                        return ResponseEntity.ok(map);*/

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (WeiboException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        /*map.put("msg","request fail");
        map.put("status",500);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);*/
        return null;
    }
}
