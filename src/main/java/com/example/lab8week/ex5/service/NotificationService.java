package com.example.lab8week.ex5.service;

import com.example.lab8week.ex5.component.MessageSender;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final MessageSender emailSender;
    private final MessageSender smsSender;

    public NotificationService(
            @Qualifier("emailSender") MessageSender emailSender,
            @Qualifier("smsSender") MessageSender smsSender
    ) {
        this.emailSender = emailSender;
        this.smsSender = smsSender;
    }

    public void notifyByEmail(String user, String message) {
        emailSender.send(user, message);
    }

    public void notifyBySms(String user, String message) {
        smsSender.send(user, message);
    }
}
