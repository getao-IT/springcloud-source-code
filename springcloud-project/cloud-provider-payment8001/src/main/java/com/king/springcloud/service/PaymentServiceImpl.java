package com.king.springcloud.service;

import ch.qos.logback.core.util.TimeUtil;
import com.king.springcloud.dao.PaymentDao;
import com.king.springcloud.entities.Payment;
import com.king.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author getao
 * @date 2022/6/30 18:55
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    @Override
    public int createPayment(Payment payment) {
        return paymentDao.createPayment(payment);
    }

    @Override
    public Payment getPaymentById(long id) {
        return paymentDao.getPaymentById(id);
    }
}
