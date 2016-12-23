package com.test.controller;

import com.test.bean.PhotoBean;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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

    /**
     * form 表单的提交方式
     * @param upload
     * @param request
     * @param response
     */
    @RequestMapping(value = "/upload",method = {RequestMethod.POST,RequestMethod.GET})
    public void uploadphoto(@RequestParam("myfile") MultipartFile upload, HttpServletRequest request, HttpServletResponse response){
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
    public static float division(float num) {
        float m = 1024 * 1024;
        float result = num / m;
        return result;
    }
    /**
     * ajax upload 压缩之后的图片
     */
    @RequestMapping(value = "/ajaxupload",method = {RequestMethod.GET,RequestMethod.POST})
    public void ajaxupload(@RequestBody PhotoBean pb, HttpServletRequest request){
    /*public void ajaxupload(HttpServletRequest request){*/
        System.out.println(pb.getPhotoUrl());
    }
}

