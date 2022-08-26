package com.king.springcloud.filter;

import cn.hutool.http.HttpStatus;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class GatewayGlobalFilterConfig implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("==========================开始执行GatewayGlobalFilter");
        /*List<String> token = exchange.getRequest().getHeaders().get("token");
        if (token == null || token.size() == 0) {
            System.out.println("==========================请求中不包含token信息，不合法的请求");
            exchange.getResponse().setStatusCode(org.springframework.http.HttpStatus.valueOf(HttpStatus.HTTP_UNAUTHORIZED));
            return exchange.getResponse().setComplete();
        }*/
        return chain.filter(exchange);
    }

    /**
     *
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
