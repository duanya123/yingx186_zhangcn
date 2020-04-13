package com.baizhi.zcn.dao;

import com.baizhi.zcn.entity.User;
import com.baizhi.zcn.entity.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper <User>{

    User queryByUsername(String usdername);
}