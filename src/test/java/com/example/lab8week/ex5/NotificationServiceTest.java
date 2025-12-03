package com.example.lab8week.ex5;

import com.example.lab8week.ex5.component.EmailSender;
import com.example.lab8week.ex5.component.MessageSender;
import com.example.lab8week.ex5.component.SmsSender;
import com.example.lab8week.ex5.service.NotificationService;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for @Qualifier example (ex5).
 * Unit tests that don't require Spring context.
 */
class NotificationServiceTest {

    @Test
    void testEmailSender() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            MessageSender emailSender = new EmailSender();
            emailSender.send("test@example.com", "Hello via Email");
            String output = outContent.toString();
            
            assertTrue(output.contains("Email to test@example.com: Hello via Email"));
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void testSmsSender() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            MessageSender smsSender = new SmsSender();
            smsSender.send("123-456-7890", "Hello via SMS");
            String output = outContent.toString();
            
            assertTrue(output.contains("SMS to 123-456-7890: Hello via SMS"));
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void testNotificationServiceWithQualifiedBeans() {
        MessageSender emailSender = new EmailSender();
        MessageSender smsSender = new SmsSender();
        NotificationService service = new NotificationService(emailSender, smsSender);
        
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            service.notifyByEmail("user@example.com", "Email message");
            service.notifyBySms("555-1234", "SMS message");
            String output = outContent.toString();
            
            assertTrue(output.contains("Email to user@example.com: Email message"));
            assertTrue(output.contains("SMS to 555-1234: SMS message"));
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void testNotifyByEmailOnly() {
        MessageSender emailSender = new EmailSender();
        MessageSender smsSender = new SmsSender();
        NotificationService service = new NotificationService(emailSender, smsSender);
        
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            service.notifyByEmail("only-email@example.com", "Only email");
            String output = outContent.toString();
            
            assertTrue(output.contains("Email to only-email@example.com: Only email"));
            assertFalse(output.contains("SMS"));
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void testNotifyBySmsOnly() {
        MessageSender emailSender = new EmailSender();
        MessageSender smsSender = new SmsSender();
        NotificationService service = new NotificationService(emailSender, smsSender);
        
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            service.notifyBySms("555-9876", "Only SMS");
            String output = outContent.toString();
            
            assertTrue(output.contains("SMS to 555-9876: Only SMS"));
            assertFalse(output.contains("Email"));
        } finally {
            System.setOut(originalOut);
        }
    }
}
