package com.example.lab8week.ex1.spring;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NotificationService {

    private final List<MessageSender> senders;

    public NotificationService(List<MessageSender> senders) {
        this.senders = senders;
    }

    public void notifyUser(String user, String message) {
        senders.forEach(sender -> sender.send(user, message));
    }
}
