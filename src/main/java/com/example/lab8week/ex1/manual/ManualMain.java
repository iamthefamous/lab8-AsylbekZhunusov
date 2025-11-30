package com.example.lab8week.ex1.manual;

// ManualMain.java
public class ManualMain {
    public static void main(String[] args) {
        // Using EmailMessageSender
        MessageSender emailSender = new EmailMessageSender();
        NotificationService notificationService = new NotificationService(emailSender);
        notificationService.notifyUser("user@example.com", "Welcome via Email!");

        // Switch to SMS
        MessageSender smsSender = new SmsMessageSender();
        notificationService = new NotificationService(smsSender);
        notificationService.notifyUser("123-456-7890", "Welcome via SMS!");
    }
}
