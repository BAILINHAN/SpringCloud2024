package com.atguigu.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient//开启服务注册发现
@SpringBootApplication
public class Main80 {

    public static void main(String[] args) {
        SpringApplication.run(Main80.class, args);
    }
}