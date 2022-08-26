package com.king.springcloud.service;

import com.king.springcloud.entities.CommonResult;
import com.king.springcloud.service.impl.OrderTransferPaymentFallBackService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT", fallback = OrderTransferPaymentFallBackService.class)
public interface OrderOpenfeignHystrixService {

    @GetMapping("/payment/hystrix/getHystrixInfo_OK")
    public CommonResult<String> getHystrixInfo_OK();

    @GetMapping("/payment/hystrix/getHystrixInfo_fallback_timeout")
    public CommonResult<String> getHystrixInfo_fallback_timeout();
}
