package com.example.lab8week.ex1.manual;

// EmailMessageSender.java
public class EmailMessageSender implements MessageSender {
    @Override
    public void send(String to, String message) {
        System.out.println("EMAIL to " + to + ": " + message);
    }
}
