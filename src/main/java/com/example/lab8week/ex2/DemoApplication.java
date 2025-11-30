package com.example.lab8week.ex2;

import com.example.lab8week.ex2.order.OrderService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    private final OrderService orderService;

    public DemoApplication(OrderService orderService) {
        this.orderService = orderService;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) {
        orderService.placeOrder("alice@example.com", 100);
    }
}
