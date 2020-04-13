package com.baizhi.zcn;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@tk.mybatis.spring.annotation.MapperScan("com.baizhi.zcn.dao")
@org.mybatis.spring.annotation.MapperScan("com.baizhi.zcn.dao")
@SpringBootApplication
public class Yingx186ZhangcnApplication {
    public static void main(String[] args) {
        SpringApplication.run(Yingx186ZhangcnApplication.class, args);
    }

}
