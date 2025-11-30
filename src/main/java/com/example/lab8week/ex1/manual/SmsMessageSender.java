package com.example.lab8week.ex1.manual;

// SmsMessageSender.java
public class SmsMessageSender implements MessageSender {
    @Override
    public void send(String to, String message) {
        System.out.println("SMS to " + to + ": " + message);
    }
}
