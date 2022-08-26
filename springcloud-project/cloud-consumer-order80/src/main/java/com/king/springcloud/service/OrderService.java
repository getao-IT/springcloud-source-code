package com.king.springcloud.service;

import com.king.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author getao
 * @date 2022/6/30 18:55
 */
public interface OrderService {

    public int createPayment(Payment payment);

    public Payment getPaymentById(@Param("id") long id);
}
