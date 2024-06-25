package com.atguigu.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient //开启向consul注册中心注册服务
@EnableFeignClients //激活openFeign
@SpringBootApplication
public class MainOpenFeign80 {

    public static void main(String[] args) {

        SpringApplication.run(MainOpenFeign80.class, args);
    }

}