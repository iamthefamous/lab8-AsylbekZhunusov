package com.example.lab8week.ex5.component;

import com.example.lab8week.ex5.component.MessageSender;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("emailSender")
public class EmailSender implements MessageSender {
    @Override
    public void send(String to, String message) {
        System.out.println("Email to " + to + ": " + message);
    }
}

