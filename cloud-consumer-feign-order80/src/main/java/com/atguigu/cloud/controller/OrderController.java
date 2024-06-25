package com.atguigu.cloud.controller;

import com.atguigu.cloud.apis.PayFeignApi;
import com.atguigu.cloud.entity.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class OrderController {
    @Resource
    private PayFeignApi payFeignApi;

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @PostMapping(value = "/feign/pay/add")
    public ResultData addOrder(@RequestBody PayDTO payDTO){

        logger.info("模拟本地addOrder新增订单成功，开启addPay支付微服务远程调用");
        return payFeignApi.addPay(payDTO);
    }

    @GetMapping(value = "/feign/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id){

        logger.info("支付微服务远程调用，按照id查询订单支付流水信息");
        ResultData payInfo = payFeignApi.getPayInfo(id);
        return payInfo;
    }

    /**
     * 演示负载均衡
     * @return
     */
    @GetMapping(value = "/feign/pay/mylb")
    public String mylb(){

        return payFeignApi.myLb();
    }

}
