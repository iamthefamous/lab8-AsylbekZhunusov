package com.example.lab8week.ex1.spring;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class EmailMessageSender implements MessageSender {
    @Override
    public void send(String to, String message) {
        System.out.println("EMAIL to " + to + ": " + message);
    }
}
