package com.atguigu.cloud.gateway;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class MyGatewayFilterFactory extends AbstractGatewayFilterFactory<MyGatewayFilterFactory.Config> {


    public MyGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(MyGatewayFilterFactory.Config config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

                ServerHttpRequest request = exchange.getRequest();
                System.out.println("进入了自定义网关过滤器MyGatewayFilterFactory, status:" + config.getStatus());
                if(request.getQueryParams().containsKey("atguigu")){

                    return chain.filter(exchange);
                }
                exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST); //参数错误，返回400错误
                return exchange.getResponse().setComplete();
            }
        };
    }

    @Data
    public static class Config{

        /**
         * 设定一个标志位
         */
        private String status;

    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("status");
    }
}
