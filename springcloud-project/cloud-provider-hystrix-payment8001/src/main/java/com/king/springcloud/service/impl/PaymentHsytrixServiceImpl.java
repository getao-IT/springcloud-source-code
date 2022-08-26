package com.king.springcloud.service.impl;

import com.king.springcloud.entities.CommonResult;
import com.king.springcloud.service.PaymentHsytrixService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class PaymentHsytrixServiceImpl implements PaymentHsytrixService {

    @Override
    public CommonResult<String> getHystrixInfo_OK() {
        return new CommonResult<String>(200, "Hystrix OK线程："+Thread.currentThread().getName()
                +" 执行成功");
    }

    /*  服务降级  */
    @HystrixCommand(fallbackMethod = "fallback_timeout_method", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    }) // 设置服务降级方法，设置报错或执行超过3s就执行降级方法
    @Override
    public CommonResult<String> getHystrixInfo_fallback_timeout() {
        try {
            //int i = 10/0;
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new CommonResult<String>(200, "Hystrix timeout线程："+Thread.currentThread().getName()
                +" 执行成功");
    }

    public CommonResult<String> fallback_timeout_method() {
        return new CommonResult<String>(200, "8001 Hystrix 服务运行出错或响应时间过长："+Thread.currentThread().getName()
                +" 执行成功o(╥﹏╥)o");
    }

    /*  服务熔断  */
    @HystrixCommand(fallbackMethod = "fallback_circuit_breaker", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), // 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), // 请求次数阈值，在时间窗口期内达到这个次数才会开启
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60") // 失败率达到多少后跳闸
    })
    @Override
    public CommonResult<String> paymnetCircuitBreaker(int id) {
        if (id < 0) {
            throw  new RuntimeException("id不能为负数o(╥﹏╥)o");
        } else {
            log.info("熔断服务调用成功^_^");
        }
        return new CommonResult<String>(200, "CircuitBreaker："+Thread.currentThread().getName()
                +" 执行成功^_^");
    }

    public CommonResult<String> fallback_circuit_breaker(int id) {
        return new CommonResult<String>(200, "CircuitBreaker Open 服务运行出错或响应时间过长："+Thread.currentThread().getName()
                +" o(╥﹏╥)o");
    }

}
