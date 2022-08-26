package com.king.springcloud.service;

import com.king.springcloud.entities.CommonResult;
import com.king.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface OrderFeignService {

    @GetMapping("/payment/getPaymentById")
    public CommonResult<Payment> getPaymentById(@RequestParam(value = "id") Long id);

    @GetMapping("/payment/getInfoFroTimeoutTest")
    public CommonResult<String> getInfoFroTimeoutTest();
}
