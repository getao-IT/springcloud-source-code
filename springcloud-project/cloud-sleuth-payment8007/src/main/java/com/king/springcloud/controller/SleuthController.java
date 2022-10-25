package com.king.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/sleuth")
public class SleuthController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/zipkin-sleuth")
    public String getZipSleuth() {
        return "支付服务8007------O(∩_∩)O哈哈~";
    }

    @GetMapping("/zipkin-sleuth-order80")
    public String invokeOrder80() {
        String url = "http://localhost:81/order/zipkin-sleuth";
        String forObject = restTemplate.getForObject(url, String.class);
        return "支付服务8007-调用成功：[" + forObject + "]";
    }
}
