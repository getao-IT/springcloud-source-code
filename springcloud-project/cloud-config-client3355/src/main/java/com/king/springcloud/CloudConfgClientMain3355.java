package com.king.springcloud;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableRabbit
public class CloudConfgClientMain3355 {
    public static void main(String[] args) {
        SpringApplication.run(CloudConfgClientMain3355.class, args);
    }
}
