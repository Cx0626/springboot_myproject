package org.example.springboot_project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Springboot启动类
@SpringBootApplication
@MapperScan({"org.example.springboot_project.mapper", "org.example.springboot_project.utils"}) //扫描mapper接口
public class SpringbootMyprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMyprojectApplication.class, args);
        System.out.println("打开网址测试 = " + "http://localhost:8088/userinfo/list");
    }

}
