package com.example.lab8week.ex2.order;

import com.example.lab8week.ex2.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private PaymentService paymentService;  // field injection

    public void placeOrder(String user, int amount) {
        paymentService.pay(user, amount);
    }
}
