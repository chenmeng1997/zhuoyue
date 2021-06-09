package com.cm.zhuoyue.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 陈萌
 * @date 2021/6/9
 */
@SpringBootApplication
@MapperScan("com.cm.zhuoyue.*.mapper")
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}