package com.king.springcloud.controller;

import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderzkController {

    @Autowired
    private RestTemplate restTemplate;

    private final String PAYMENT_URL = "http://cloud-provider-payment";

    @GetMapping("/getOrderZkInfo")
    public String getOrderZkInfo() {
        String url = PAYMENT_URL + "/payment/getUuid";
        String paymentInfo = restTemplate.getForObject(url, String.class);
        return "orderzk 调用成功：" + paymentInfo;
    }
}
