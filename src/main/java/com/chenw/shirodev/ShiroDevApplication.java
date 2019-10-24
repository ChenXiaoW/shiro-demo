package com.chenw.shirodev;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.chenw.shirodev.dao")
@SpringBootApplication
public class ShiroDevApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShiroDevApplication.class, args);
    }

}
