package com.atguigu.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import tk.mybatis.spring.annotation.MapperScan;

@EnableDiscoveryClient//开启服务发现
@SpringBootApplication
@MapperScan("com.atguigu.cloud.mapper")//表示该包下的增删改查都能扫描的到
@RefreshScope//开启consul配置动态刷新
public class Main8001 {
    public static void main(String[] args) {

        //启动SpringBoot
        SpringApplication.run(Main8001.class, args);
    }
}