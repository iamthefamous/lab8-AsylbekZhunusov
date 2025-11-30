package com.example.lab8week.ex5.controller;

import com.example.lab8week.ex5.service.NotificationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notify")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/email")
    public String sendEmail(@RequestParam String to, @RequestParam String message) {
        notificationService.notifyByEmail(to, message);
        return "Email notification sent to " + to;
    }

    @PostMapping("/sms")
    public String sendSms(@RequestParam String to, @RequestParam String message) {
        notificationService.notifyBySms(to, message);
        return "SMS notification sent to " + to;
    }
}
