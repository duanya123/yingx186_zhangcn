package com.baizhi.zcn;

import com.baizhi.zcn.dao.VideoMapper;
import com.baizhi.zcn.po.VideoPo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTests {

    @Resource
    RedisTemplate redisTemplate;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Test
    public void testqueryss(){

        /*Set keys = redisTemplate.keys("*");
        for (Object key : keys) {
            System.out.println(key);
        }*/

        //redisTemplate.opsForValue().set("name","xiaohei");

        /*ValueOperations valueOperations = redisTemplate.opsForValue();
        Object name = valueOperations.get("name");
        System.out.println(name);*/

        ValueOperations<String, String> stringValueOperations = stringRedisTemplate.opsForValue();
        //stringValueOperations.set("name","xiaohei");
        //String name = stringValueOperations.get("name");
        //System.out.println(name);

        //stringRedisTemplate.expire("name",20000, TimeUnit.MILLISECONDS);

        for (int i = 0; i < 200; i++) {
            Boolean name = stringRedisTemplate.hasKey("name");
            System.out.println(name);
        }


    }

}
