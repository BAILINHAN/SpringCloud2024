package com.atguigu.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class RateLimitController {

    @GetMapping("/rateLimit/byUrl")
    public String byUrl(){

        return "按rest地址限流";
    }

    @GetMapping("/rateLimit/byResource")
    @SentinelResource(value = "byResource", blockHandler = "blockHandler")
    public String byResource(){

        return "按照资源名称sentinelResource限流测试";
    }

    public String blockHandler(BlockException blockException){

        return "服务不可用，触发了@SentinelResource";
    }

    @RequestMapping("/rateLimit/doAction/{p1}")
    @SentinelResource(value = "doActionSentinel", blockHandler = "doActionHandler", fallback = "doActionFallback")
    public String doAction(@PathVariable("p1") Integer p1){

        if(p1 == 0){

            throw new RuntimeException("p1等于零直接异常");
        }
        return "doAction";
    }

    public String doActionHandler(@PathVariable("p1") Integer p1, BlockException e){

        log.error("sentinel配置自定义限流:{}", e);
        return "sentinel配置自定义限流";
    }

    public String doActionFallback(@PathVariable("p1") Integer p1, Throwable e){

        log.error("程序逻辑异常," + "p1=" + p1 + ",e=" + e);
        return "程序逻辑异常" + "e:" + e;
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "testHotHandler")
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2){

        return "------testHotKey";
    }

    public String testHotHandler(String p1, String p2, BlockException e){

        return "------dealHandler_testHotKey";
    }

}
