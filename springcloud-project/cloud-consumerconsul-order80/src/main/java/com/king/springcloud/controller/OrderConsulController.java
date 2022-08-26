package com.king.springcloud.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderConsulController {

    @Autowired
    private RestTemplate restTemplate;

    private final String PAYMENT_URL = "http://cloud-payment-service";

    @GetMapping("/getOrderConsulInfo")
    public String getOrderConsulInfo() {
        String url = PAYMENT_URL + "/payment/getConsulInfo";
        String paymentInfo = restTemplate.getForObject(url, String.class);
        return "orderconsul 调用成功：" + paymentInfo;
    }
}
