package com.atguigu.cloud.gateway;

import io.micrometer.common.util.StringUtils;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * 自定义路由Factory
 */
@Component
public class MyRoutePredicateFactory extends AbstractRoutePredicateFactory<MyRoutePredicateFactory.Config> {


    /**
     * 构造方法
     */
    public MyRoutePredicateFactory() {
        super(MyRoutePredicateFactory.Config.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(MyRoutePredicateFactory.Config config) {
        return new Predicate<ServerWebExchange>() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {

                //http://localhost:9527/pay/gateway/get/1?userType=diamond
                String userType = serverWebExchange.getRequest().getQueryParams().getFirst("userType");

                //userType为空
                if(StringUtils.isBlank(userType)){

                    //返回失败
                    return false;
                }

                //如果等于yml文件中配置的值
                if(userType.equalsIgnoreCase(config.getUserType())){

                    //可以访问
                    return true;
                }

                return false;
            }
        };
    }

    /**
     * 路由规则
     */
    @Validated
    public static class Config{

        @NotEmpty
        private String userType;

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }
    }

    /**
     * 支持短促格式
     * @return
     */
    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("userType");
    }
}
