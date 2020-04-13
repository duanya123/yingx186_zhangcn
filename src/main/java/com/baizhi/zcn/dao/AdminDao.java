package com.baizhi.zcn.dao;

import com.baizhi.zcn.entity.Admin;

public interface AdminDao {

    Admin queryByUsername(String username);
}
