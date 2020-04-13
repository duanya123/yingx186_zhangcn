package com.baizhi.zcn;

import com.baizhi.zcn.dao.AdminDao;
import com.baizhi.zcn.dao.UserMapper;
import com.baizhi.zcn.dao.VideoMapper;
import com.baizhi.zcn.entity.Admin;
import com.baizhi.zcn.entity.User;
import com.baizhi.zcn.entity.UserExample;
import com.baizhi.zcn.po.VideoPo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JavaTests {

    @Resource
    VideoMapper videoMapper;

    @Test
    public void testqueryss(){
        List<VideoPo> videoPos = videoMapper.queryByReleaseTime();
        videoPos.forEach(videoPo -> System.out.println(videoPo));
    }



}
