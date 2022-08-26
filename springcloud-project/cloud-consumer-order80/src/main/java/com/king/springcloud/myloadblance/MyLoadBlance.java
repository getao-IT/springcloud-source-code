package com.king.springcloud.myloadblance;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface MyLoadBlance {

    public ServiceInstance lbInstance(String serviceId);
}
