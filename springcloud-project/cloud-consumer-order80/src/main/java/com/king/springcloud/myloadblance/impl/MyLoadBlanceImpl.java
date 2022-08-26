package com.king.springcloud.myloadblance.impl;

import com.king.springcloud.myloadblance.MyLoadBlance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Slf4j
public class MyLoadBlanceImpl implements MyLoadBlance {

    @Autowired
    private DiscoveryClient discoveryClient;

    private AtomicInteger nextServerCycliccounter;

    public MyLoadBlanceImpl() {
        this.nextServerCycliccounter = new AtomicInteger(0);
    }

    /**
     * 轮询策略获取负载服务
     * @param serviceId
     * @return 服务实例
     */
    @Override
    public ServiceInstance lbInstance(String serviceId) {
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceId);
        if (serviceInstances == null || serviceInstances.size() == 0) {
            log.error("相关服务列表数据为空：SERVICE LIST IS EMPTY");
            return null;
        }
        int currentIndex = getServerAndIncrement(serviceInstances.size());
        return serviceInstances.get(currentIndex);
    }

    /**
     * 增加服务调用累计变量并且获取服务实例索引
     * @return
     */
    private int getServerAndIncrement(int serverTotal) {
        int current;
        int next;
        do {
            current = this.nextServerCycliccounter.get();
            next = current >= Integer.MAX_VALUE ? 0 : (current + 1) % serverTotal;
        } while (!this.nextServerCycliccounter.compareAndSet(current, next));
        return next;
    }
}
