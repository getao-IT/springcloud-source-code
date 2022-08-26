package com.king.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author getao
 * @date 2022/7/1 11:27
 */
@Configuration
public class RestemplateConfig {

    @Bean
    // @LoadBalanced // 开启RestTemplate负载均衡支持
    public RestTemplate getTemplate() {
        return new RestTemplate();
    }
}
