package com.baizhi.zcn.controller;

import com.baizhi.zcn.entity.User;
import com.baizhi.zcn.service.UserService;
import com.baizhi.zcn.util.AliyunSendPhoneUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

@Controller
@RequestMapping("user")
public class UserController {

    @Resource
    UserService userService;


    @RequestMapping("queryByPage")
    @ResponseBody
    public HashMap<String,Object> queryByPage(Integer page,Integer rows){
        HashMap<String, Object> map = userService.queryByPage(page, rows);
        return map;
    }

    @RequestMapping("edit")
    @ResponseBody
    public String edit(User user,String oper){

        System.out.println(user);

        String uid =null;
        if(oper.equals("add")){
            System.out.println(user);
            uid = userService.add(user);
        }

        if(oper.equals("edit")){
            userService.update(user);
        }

        if(oper.equals("del")){

        }
        return uid;
    }

    //文件上传
    @RequestMapping("uploadUser")
    public void uploadUser(MultipartFile headImg, String id, HttpServletRequest request){
        //userService.uploadUser(headImg,id,request);  上传到本地
        userService.uploadUserAliyuns(headImg,id,request);  //上传到阿里云
    }

    @RequestMapping("sendPhoneCode")
    @ResponseBody
    public String sendPhoneCode(String phone){

        //获取随机数
        String random = AliyunSendPhoneUtil.getRandom(6);

        System.out.println("存储验证码："+random);

        //发送验证码
        String message = AliyunSendPhoneUtil.sendCode(phone, random);

        System.out.println(message);
        return message;
    }

}
