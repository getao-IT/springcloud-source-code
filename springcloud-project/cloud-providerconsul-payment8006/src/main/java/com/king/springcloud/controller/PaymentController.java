package com.king.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @GetMapping("/getConsulInfo")
    public String getConsulInfo() {
        return "consul 访问成功：" + UUID.randomUUID().toString();
    }
}
