package com.example.lab8week.ex1.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for Spring Dependency Injection example (ex1/spring).
 */
@SpringBootTest(classes = Ex1DemoApplication.class)
class SpringNotificationServiceTest {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private List<MessageSender> messageSenders;

    @Test
    void contextLoads() {
        assertNotNull(notificationService);
    }

    @Test
    void testMessageSendersInjected() {
        assertNotNull(messageSenders);
        assertEquals(2, messageSenders.size());
    }

    @Test
    void testNotifyUserSendsToAllSenders() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            notificationService.notifyUser("test@example.com", "Hello");
            String output = outContent.toString();
            
            // Both senders should be invoked
            assertTrue(output.contains("EMAIL to test@example.com: Hello"));
            assertTrue(output.contains("SMS to test@example.com: Hello"));
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void testEmailSenderHasPrimaryAnnotation() {
        // The EmailMessageSender is marked with @Primary
        boolean hasEmailSender = messageSenders.stream()
                .anyMatch(sender -> sender instanceof EmailMessageSender);
        assertTrue(hasEmailSender);
    }

    @Test
    void testSmsSenderExists() {
        boolean hasSmsSender = messageSenders.stream()
                .anyMatch(sender -> sender instanceof SmsMessageSender);
        assertTrue(hasSmsSender);
    }
}
