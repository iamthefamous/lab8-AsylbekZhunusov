package com.example.lab8week.ex1.manual;

// NotificationService.java
public class NotificationService {
    private final MessageSender sender;

    public NotificationService(MessageSender sender) {
        this.sender = sender;
    }

    public void notifyUser(String user, String message) {
        sender.send(user, message);
    }
}
