package com.king.springcloud.controller;

import com.king.springcloud.entities.CommonResult;
import com.king.springcloud.entities.Payment;
import com.king.springcloud.service.OrderFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/order")
public class OrderFeignController {

    @Autowired
    private OrderFeignService orderFeignService;

    @GetMapping("/openfeign/getPaymentById")
    public CommonResult<Payment> getPaymentById(Long id) {
        CommonResult<Payment> payment = orderFeignService.getPaymentById(id);
        return payment;
    }

    @GetMapping("/openfeign/timeoutConfig/getPaymentById")
    public CommonResult<String> getPaymentByIdForTimeout() {
        CommonResult<String> payment = orderFeignService.getInfoFroTimeoutTest();
        return payment;
    }
}
