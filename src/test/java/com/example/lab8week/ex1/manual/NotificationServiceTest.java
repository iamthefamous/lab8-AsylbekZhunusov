package com.example.lab8week.ex1.manual;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for Manual Dependency Injection example (ex1/manual).
 */
class NotificationServiceTest {

    @Test
    void testEmailMessageSender() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            EmailMessageSender emailSender = new EmailMessageSender();
            emailSender.send("test@example.com", "Hello");
            
            assertTrue(outContent.toString().contains("EMAIL to test@example.com: Hello"));
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void testSmsMessageSender() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            SmsMessageSender smsSender = new SmsMessageSender();
            smsSender.send("123-456-7890", "Hello");
            
            assertTrue(outContent.toString().contains("SMS to 123-456-7890: Hello"));
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void testNotificationServiceWithEmailSender() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            MessageSender emailSender = new EmailMessageSender();
            NotificationService service = new NotificationService(emailSender);
            service.notifyUser("user@example.com", "Welcome!");
            
            assertTrue(outContent.toString().contains("EMAIL to user@example.com: Welcome!"));
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void testNotificationServiceWithSmsSender() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            MessageSender smsSender = new SmsMessageSender();
            NotificationService service = new NotificationService(smsSender);
            service.notifyUser("555-1234", "Welcome!");
            
            assertTrue(outContent.toString().contains("SMS to 555-1234: Welcome!"));
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void testMessageSenderInterface() {
        // Verify both implementations implement the MessageSender interface
        MessageSender emailSender = new EmailMessageSender();
        MessageSender smsSender = new SmsMessageSender();
        
        assertNotNull(emailSender);
        assertNotNull(smsSender);
        assertTrue(emailSender instanceof MessageSender);
        assertTrue(smsSender instanceof MessageSender);
    }
}
