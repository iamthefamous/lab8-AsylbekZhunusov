package com.example.lab8week.ex2;

import com.example.lab8week.ex2.order.OrderService;
import com.example.lab8week.ex2.payment.CreditCardPaymentService;
import com.example.lab8week.ex2.payment.PaymentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for Field Injection example (ex2).
 */
@SpringBootTest(classes = Ex2DemoApplication.class)
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PaymentService paymentService;

    @Test
    void contextLoads() {
        assertNotNull(orderService);
        assertNotNull(paymentService);
    }

    @Test
    void testPaymentServiceIsCreditCard() {
        assertTrue(paymentService instanceof CreditCardPaymentService);
    }

    @Test
    void testPlaceOrder() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            orderService.placeOrder("alice@example.com", 100);
            String output = outContent.toString();
            
            assertTrue(output.contains("Charging 100 to alice@example.com by card"));
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void testPlaceOrderWithDifferentAmounts() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            orderService.placeOrder("bob@example.com", 250);
            String output = outContent.toString();
            
            assertTrue(output.contains("Charging 250 to bob@example.com by card"));
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void testPaymentServicePay() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            paymentService.pay("test@example.com", 500);
            String output = outContent.toString();
            
            assertTrue(output.contains("Charging 500 to test@example.com by card"));
        } finally {
            System.setOut(originalOut);
        }
    }
}
