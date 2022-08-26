package com.king.springcloud.service;

import com.king.springcloud.entities.CommonResult;

public interface PaymentHsytrixService {

    public CommonResult<String> getHystrixInfo_OK();

    public CommonResult<String> getHystrixInfo_fallback_timeout();

    public CommonResult<String> paymnetCircuitBreaker(int id);
}
