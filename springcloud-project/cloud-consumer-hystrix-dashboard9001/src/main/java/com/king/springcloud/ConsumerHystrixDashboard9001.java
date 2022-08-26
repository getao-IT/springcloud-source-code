package com.king.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard // 开启Hystrix Dashboard服务监控支持
public class ConsumerHystrixDashboard9001 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerHystrixDashboard9001.class, args);
    }
}
