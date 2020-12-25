package com.homeworkh.usermanagementserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.homeworkh.usermanagementserver.mapper")
public class UserManagementServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserManagementServerApplication.class, args);
    }

}
