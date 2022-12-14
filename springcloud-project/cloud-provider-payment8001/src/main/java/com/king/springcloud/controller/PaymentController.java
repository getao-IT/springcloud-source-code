package com.king.springcloud.controller;

import com.king.springcloud.entities.CommonResult;
import com.king.springcloud.entities.Payment;
import com.king.springcloud.service.PaymentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author getao
 * @date 2022/6/30 18:58
 */
@ResponseBody
@Controller
@Slf4j
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentServiceImpl paymentService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/createPayment")
    public CommonResult<Integer> createPayment(Payment payment) {
        int result = paymentService.createPayment(payment);
        log.info("插入结果为：{} ", result);
        if (result != 0) {
            return new CommonResult<Integer>(200, "数据创建成功，port" + serverPort, result);
        } else {
            return new CommonResult<Integer>(201, "插入失败，port" + serverPort, result);
        }
    }

    @GetMapping("/getPaymentById")
    public CommonResult<Payment> getPaymentById(Long id) {
        Payment result = paymentService.getPaymentById(id);
        log.info("查询结果为：{} ", result);
        if (result != null) {
            return new CommonResult<Payment>(200, "数据查询成功，port"+serverPort, result);
        } else {
            return new CommonResult<Payment>(201, "没有符合条件的记录，port"+serverPort, result);
        }
    }

    @GetMapping("/getServerInfo")
    public CommonResult<List<List<ServiceInstance>>> getServerInfo(Long id) {
        List<List<ServiceInstance>> result = new ArrayList<>();
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("------------服务"+service+"---------------");
            List<ServiceInstance> instances = discoveryClient.getInstances(service);
            if (instances == null || instances.size() == 0) {
                log.info("------------服务列表为空---------------\n\n\n");
                continue;
            }
            result.add(instances);
            for (ServiceInstance instance : instances) {
             log.info("服务ID：" + instance.getServiceId() + "服务ip：" + instance.getHost() + "，服务port：" + instance.getPort() + "，服务地址：" + instance.getUri());
            }
        }
        return new CommonResult<List<List<ServiceInstance>>>(200, "获取服务信息成功！", result);
    }

    @GetMapping("/getInfoFroTimeoutTest")
    public CommonResult<String> getInfoFroTimeoutTest() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new CommonResult<String>(0, "Payment8001处理时长：3s，请求成功！", null);
    }

    @GetMapping("/zipkin-sleuth")
    public String getZipSleuth() {
        return "支付服务8001------O(∩_∩)O哈哈~";
    }
}
