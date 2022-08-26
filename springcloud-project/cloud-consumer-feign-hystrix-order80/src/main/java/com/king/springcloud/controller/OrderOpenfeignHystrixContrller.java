package com.king.springcloud.controller;

import com.king.springcloud.entities.CommonResult;
import com.king.springcloud.service.OrderOpenfeignHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import feign.Param;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "hystrixInfo_fallback_timeout", commandProperties = {
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
})
public class OrderOpenfeignHystrixContrller {

    @Resource
    private OrderOpenfeignHystrixService orderOpenfeignHystrixService;

    @GetMapping("/order/hystrix/getHystrixInfo_OK")
    public CommonResult<String> getHystrixInfo_OK() {
        CommonResult<String> result = orderOpenfeignHystrixService.getHystrixInfo_OK();
        return result;
    }

    /*@HystrixCommand(fallbackMethod = "hystrixInfo_fallback_timeout", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })*/ // 每个都要写，太麻烦，可以处理
    @GetMapping("/order/hystrix/getHystrixInfo_fallback_timeout")
    @HystrixCommand // 通过这个注解，如果没有指定特定的fallback方法，就找global 的fallback
    public CommonResult<String> getHystrixInfo_fallback_timeout() {
        CommonResult<String> result = orderOpenfeignHystrixService.getHystrixInfo_fallback_timeout();
        return result;
    }

    public CommonResult<String> hystrixInfo_fallback_timeout() {
        return new CommonResult<String>(200, "消费者调用8001支付服务：运行报错或系统响应时间过长o(╥﹏╥)o");
    }


}
