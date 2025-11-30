package com.example.lab8week.ex2.payment;

import org.springframework.stereotype.Service;

@Service
public class CreditCardPaymentService implements PaymentService {
    @Override
    public void pay(String user, int amount) {
        System.out.println("Charging " + amount + " to " + user + " by card");
    }
}
