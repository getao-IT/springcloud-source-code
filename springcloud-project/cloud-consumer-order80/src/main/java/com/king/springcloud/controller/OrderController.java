package com.king.springcloud.controller;

import com.king.springcloud.entities.CommonResult;
import com.king.springcloud.entities.Payment;
import com.king.springcloud.myloadblance.MyLoadBlance;
import com.king.springcloud.service.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import javax.servlet.annotation.WebFilter;

/**
 * @author getao
 * @date 2022/6/30 18:58
 */
@WebFilter
@ResponseBody
@Controller
@Slf4j
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @Resource
    private OrderServiceImpl orderService;

    @Autowired
    private MyLoadBlance myLoadBlance; // 自定义的轮询负载策略

    //private final String PAYMENT_URL = "http://localhost:8001/payment";
    private final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @PostMapping("/createPayment")
    public CommonResult<Integer> createPayment(@RequestBody Payment payment) {
        String url = this.PAYMENT_URL + "/payment/createPayment";
        return restTemplate.postForObject(url, payment, CommonResult.class);
    }

    @GetMapping("/getPaymentById")
    public CommonResult<Payment> getPaymentById(Long id) {
        String url = this.PAYMENT_URL + "/payment/getPaymentById";
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(url).queryParam("id", id).build().encode();
        return restTemplate.getForObject(uriComponents.toUri(), CommonResult.class);
    }

    @GetMapping("/myLoadBlance/getPaymentById")
    public CommonResult<Payment> getPaymentByIdFormyLb(Long id) {
        String url = myLoadBlance.lbInstance("CLOUD-PAYMENT-SERVICE").getUri() + "/payment/getPaymentById";
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(url).queryParam("id", id).build().encode();
        return restTemplate.getForObject(uriComponents.toUri(), CommonResult.class);
    }

    @GetMapping("/zipkin-sleuth")
    public String getZipSleuth() {
        String url = "http://localhost:8001/payment/zipkin-sleuth";
        String forObject = restTemplate.getForObject(url, String.class);
        return "订单服务80-调用成功：" + forObject.toString();
    }
}
