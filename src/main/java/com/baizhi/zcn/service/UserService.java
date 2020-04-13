package com.baizhi.zcn.service;

import com.baizhi.zcn.entity.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public interface UserService {

    HashMap<String,Object> queryByPage(Integer page, Integer rows);

    String add(User user);

    void uploadUser(MultipartFile headImg, String id, HttpServletRequest request);

    void update(User user);

    void uploadUserAliyun(MultipartFile headImg, String id, HttpServletRequest request);

    void uploadUserAliyuns(MultipartFile headImg, String id, HttpServletRequest request);

}
