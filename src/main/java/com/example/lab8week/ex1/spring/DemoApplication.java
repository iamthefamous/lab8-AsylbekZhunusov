package com.example.lab8week.ex1.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    private final NotificationService notificationService;  // NOT static

    // Constructor injection
    public DemoApplication(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);  // static main is fine
    }

    // Instance method! Do NOT make static
    @Override
    public void run(String... args) {
        notificationService.notifyUser("user@example.com", "Welcome from Spring!");
    }
}
