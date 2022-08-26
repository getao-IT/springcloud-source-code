package com.king.springcloud.controller;

import com.king.springcloud.entities.CommonResult;
import com.king.springcloud.service.PaymentHsytrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentHsytrixService paymentHsytrixService;

    @GetMapping("/payment/hystrix/getHystrixInfo_OK")
    public CommonResult<String> getHystrixInfo_OK() {
        CommonResult<String> result = paymentHsytrixService.getHystrixInfo_OK();
        return result;
    }

    @GetMapping("/payment/hystrix/getHystrixInfo_fallback_timeout")
    public CommonResult<String> getHystrixInfo_fallback_timeout() {
        CommonResult<String> result = paymentHsytrixService.getHystrixInfo_fallback_timeout();
        return result;
    }

    /**
     * 服务熔断
     * @return
     */
    @GetMapping("/payment/hystrix/fallback_circuit_breaker")
    public CommonResult<String> fallback_circuit_breaker(int id) {
        CommonResult<String> result = paymentHsytrixService.paymnetCircuitBreaker(id);
        return result;
    }
}
