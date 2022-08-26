package com.king.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GateWayConfig {

    @Bean
    public RouteLocator getRoutesSogou(RouteLocatorBuilder locatorBuilder) {
        RouteLocatorBuilder.Builder routes = locatorBuilder.routes();
        routes.route("gateway_guonei", r -> r
                .path("/guonei")
                .uri("http://news.baidu.com/guonei")).build();
        routes.route("gateway_guoji", r -> r
                .path("/guoji")
                .uri("http://news.baidu.com/guoji")).build();
        return routes.build();
    }

    @Bean
    public RouteLocator getRoutesBaidu(RouteLocatorBuilder locatorBuilder) {
        RouteLocatorBuilder.Builder routes = locatorBuilder.routes();
        routes.route("gateway_baidu", r -> r
                .path("/gateway_baidu")
                .uri("http://www.baidu.com")).build();
        return routes.build();
    }
}
