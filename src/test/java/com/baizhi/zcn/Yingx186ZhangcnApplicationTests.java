package com.baizhi.zcn;

import com.baizhi.zcn.dao.AdminDao;
import com.baizhi.zcn.dao.UserMapper;
import com.baizhi.zcn.entity.Admin;
import com.baizhi.zcn.entity.User;
import com.baizhi.zcn.entity.UserExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Yingx186ZhangcnApplicationTests {

    @Resource
    AdminDao adminDao;

    @Resource
    UserMapper userMapper;

    @Test
    public void testqueryss(){
        User user = userMapper.queryByUsername("zcn");
        System.out.println(user);
    }

    @Test
    public void testquery(){
        List<User> users = userMapper.selectAll();
        users.forEach(user -> System.out.println(user));
    }

    @Test
    public void testquerys(){
        UserExample example = new UserExample();
        //example.createCriteria()
        List<User> users = userMapper.selectByExample(example);
        users.forEach(user -> System.out.println(user));
    }


    /*@Test
    public void testquery(){
        //条件对象
        UserExample example = new UserExample();
        example.createCriteria().andIdEqualTo("1");

        List<User> users = userMapper.selectByExample(example);

        users.forEach(user -> System.out.println(user));
    }


    @Test
    public void testqueryCount(){
        //条件对象
        UserExample example = new UserExample();

        long l = userMapper.countByExample(example);
        System.out.println(l);

    }

    @Test
    public void delete(){
        //条件对象
        UserExample example = new UserExample();
        example.createCriteria().andIdEqualTo("6");

        //删除
        userMapper.deleteByExample(example);
    }

    @Test
    public void save(){
        //条件对象
        UserExample example = new UserExample();
        example.createCriteria().andIdEqualTo("3");

        User user = new User();
        user.setId("3");
        user.setUsername("aaaaa");

        //修改
        //userMapper.updateByExample(user,example);
        userMapper.updateByExampleSelective(user,example);


//User user = new User("6","zcns",null,"aa","aa","aa","aa",new Date());
        //userMapper.insertSelective(user);
        //users.forEach(user -> System.out.println(user));
    }

    @Test
    public void queryAll() {
        List<User> users = userMapper.queryAll();
        users.forEach(user -> System.out.println(user));
    }*/


    @Test
    public void contextLoads() {
        Admin admin = adminDao.queryByUsername("admin");
        System.out.println(admin);
    }

}
