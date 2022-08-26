package com.king.springcloud.dao;

import com.king.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author getao
 * @date 2022/6/30 15:33
 */
@Mapper
public interface PaymentDao {

    int createPayment(Payment payment);

    Payment getPaymentById(@Param("id") long id);
}
