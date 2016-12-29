package com.test.controller;

import com.test.bean.PhotoBean;
import com.test.service.PicService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.util.Calendar;

/**
 * Created by yhang on 2016/12/21.
 */
@Controller
@RequestMapping("/photo")
public class PicController {
    static Logger logger = Logger.getLogger(PicController.class.getClass());
    //定义可以接收的图片类型
    private static String[] TYPES = {".jpg",".png",".gif"};
    private static String PHOTOPATH = "/tmp/picture";
    private static String PHOTOURL = "http://120.77.169.190/";

    @Autowired
    private PicService picService;

    @RequestMapping(value = "/upload",method = {RequestMethod.POST})
    public ResponseEntity<Void> upload(HttpServletRequest request, HttpServletResponse response){
       // @RequestBody PhotoBean pb2,
        String photoUrl = request.getParameter("img");
        String userid = request.getParameter("userid");
        //data:image/jpeg;base64,/9j/4    切分去掉base标志
        int i = StringUtils.indexOf(photoUrl, ",");
        if(i<0 || StringUtils.isBlank(userid)){
            return ResponseEntity.ok(null);
        }
        //System.out.println(",出现的角标位置为："+i);
        photoUrl = StringUtils.substring(photoUrl,i+1);
        System.out.println(photoUrl);
        String photoname = request.getParameter("photoname");
       // System.out.println(pb2.getPhotoUrl());
        File file2 = new File(photoUrl);
        logger.info("执行上传图片方法");

        PhotoBean pb = new PhotoBean();
        //获取文件
        //设置上传路径
        String fileFolder =  PHOTOPATH+ File.separator;
        //自定义文件路径
        String filePath = fileFolder+ Calendar.getInstance().get(Calendar.YEAR) + File.separator + (Calendar.getInstance().get(Calendar.MONTH)+1)+File.separator+Calendar.getInstance().get(Calendar.DATE);
        //判断该目录结构是否存在
        File file = new File(filePath);
        if(!file.isDirectory()){
            file.mkdirs();
        }
        //以当前的时间戳重命名文件
        filePath = filePath+File.separator+(System.currentTimeMillis())+"."+StringUtils.substringAfterLast(photoname,".");
        //创建文件
        File file1 = new File(filePath);
        try {
            boolean flag = string2Image(photoUrl, filePath);
            //upload.transferTo(file1);
            System.out.println("是否生成图片:"+flag);
            //设置图片的url
            pb.setPhotoUrl(filePath);
            pb.setUserId(userid);
            System.out.println(userid);
            //保存图片信息到mysql
            int num = picService.savePhotoInfo(pb);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    /**
     * form 表单的提交方式
     * @param upload
     * @param request
     * @param response
     */
    @RequestMapping(value = "/upload2",method = {RequestMethod.POST,RequestMethod.GET})
    public void uploadphoto2(@RequestParam("myfile") MultipartFile upload, HttpServletRequest request, HttpServletResponse response){
        logger.info("执行上传图片方法");
        //校验图片类型
        String suffix = upload.getOriginalFilename();

        for (String type : TYPES){
            if(!StringUtils.endsWithIgnoreCase(suffix ,type)){
                logger.info("上传图片类型error："+suffix);
                try {
                    PrintWriter out = response.getWriter();
                    out.print("上传图片类型error");
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        PhotoBean pb = new PhotoBean();
        float size = (float) upload.getSize();
        float division = division(size);
        // 字节转换成兆保存，保留两位小数
        BigDecimal bd = new BigDecimal(division);
        float result = bd.setScale(2, BigDecimal.ROUND_HALF_UP)
                .floatValue();
        System.out.println("文件大小为:"+result);
        //获取文件
        //设置上传路径
        String fileFolder =  PHOTOPATH+ File.separator;
        //自定义文件路径
        String filePath = fileFolder+ Calendar.getInstance().get(Calendar.YEAR) + File.separator + (Calendar.getInstance().get(Calendar.MONTH)+1)+File.separator+Calendar.getInstance().get(Calendar.DATE);
        //判断该目录结构是否存在
        File file = new File(filePath);
        if(!file.isDirectory()){
            file.mkdirs();
        }
        //以当前的时间戳重命名文件
        filePath = filePath+File.separator+(System.currentTimeMillis())+"."+StringUtils.substringAfterLast(suffix,".");
        //创建文件
        File file1 = new File(filePath);
        try {
            upload.transferTo(file1);
            //设置图片的url
            pb.setPhotoUrl(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 计算文件大小
     * @param num
     * @return
     */
    public static float division(float num) {
        float m = 1024 * 1024;
        float result = num / m;
        return result;
    }
    /**
     * ajax upload 压缩之后的图片
     */
    @RequestMapping(value = "/ajaxupload",method = {RequestMethod.GET,RequestMethod.POST})
    /*public void ajaxupload(@RequestBody PhotoBean pb, HttpServletRequest request){*/
    public void ajaxupload(HttpServletRequest request,HttpServletRequest response){

        String callback = request.getParameter("callback");
        String img = request.getParameter("img");
        //MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
        System.out.println(callback);
        System.out.println(img);
    }
    @RequestMapping(value = "/ajax",method = {RequestMethod.GET,RequestMethod.POST})
    public ResponseEntity ajax(HttpServletRequest request,HttpServletRequest response){
        System.out.println(request.getParameter("img"));
        String result = "hang" + "({\"abc\":123})";
        return ResponseEntity.ok(result);
    }
    /**
     * 通过BASE64Decoder解码，并生成图片
     * @param imgStr 解码后的string
     */
    public boolean string2Image(String imgStr, String imgFilePath) {
        // 对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null)
            return false;
        try {
            // Base64解码
            byte[] b = new BASE64Decoder().decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    // 调整异常数据
                    b[i] += 256;
                }
            }
            // 生成Jpeg图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    @RequestMapping("/run")
    public void run(){
        picService.run(1);
    }
}

