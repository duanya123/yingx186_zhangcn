package com.baizhi.zcn.service;

import com.baizhi.zcn.entity.Admin;

import java.util.HashMap;

public interface AdminService {

    HashMap<String, Object> login(Admin admin,String enCode);
}
