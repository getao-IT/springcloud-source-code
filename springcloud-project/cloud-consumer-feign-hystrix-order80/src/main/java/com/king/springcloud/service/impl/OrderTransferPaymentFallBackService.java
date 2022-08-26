package com.king.springcloud.service.impl;

import com.king.springcloud.entities.CommonResult;
import com.king.springcloud.service.OrderOpenfeignHystrixService;
import org.springframework.stereotype.Component;

@Component
public class OrderTransferPaymentFallBackService implements OrderOpenfeignHystrixService {

    @Override
    public CommonResult<String> getHystrixInfo_OK() {
        return new CommonResult<String>(200, "消费者调用8001-OK支付服务：运行报错或系统响应时间过长o(╥﹏╥)o");
    }

    @Override
    public CommonResult<String> getHystrixInfo_fallback_timeout() {
        return new CommonResult<String>(200, "消费者调用8001-Timeout支付服务：运行报错或系统响应时间过长o(╥﹏╥)o");
    }
}
