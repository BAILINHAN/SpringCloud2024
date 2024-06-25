package com.atguigu.cloud.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    /**
     * 配置重试机制
     * @return
     */
    @Bean
    public Retryer myRetryer(){

        //最大请求次数 maxAttempts(1首次+2重试，共计3次)，初始间隔时间100ms，重试最大间隔时间1s
        return new Retryer.Default(100, 1, 3);
    }

    /**
     * 配置feign日志级别
     * @return
     */
    @Bean
    public Logger.Level feignLoggerLevel(){

        return Logger.Level.FULL; //日志全部打印
    }

}
