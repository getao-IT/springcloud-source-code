package com.king.springcloud.controller;

import com.king.springcloud.entities.CommonResult;
import com.king.springcloud.entities.Payment;
import com.king.springcloud.service.PaymentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/createPayment")
    public CommonResult<Integer> createPayment(Payment payment) {
        int result = paymentService.createPayment(payment);
            log.info("插入结果为：{} ",result);
            if(result != 0){
            return new CommonResult<Integer>(200, "数据创建成功，port"+serverPort, result);
        } else{
            return new CommonResult<Integer>(201, "插入失败，port"+serverPort, result);
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
}
