package com.example.lab8week.ex4;

import com.example.lab8week.ex4.config.DiscountConfig;
import com.example.lab8week.ex4.config.DiscountPolicy;
import com.example.lab8week.ex4.config.PercentageDiscountPolicy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for Bean Configuration example (ex4).
 */
@SpringBootTest
@ContextConfiguration(classes = DiscountConfig.class)
class DiscountConfigTest {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private DiscountPolicy discountPolicy;

    @Test
    void contextLoads() {
        assertNotNull(context);
    }

    @Test
    void testDiscountPolicyBeanExists() {
        assertNotNull(discountPolicy);
    }

    @Test
    void testDiscountPolicyIsPercentageBased() {
        assertTrue(discountPolicy instanceof PercentageDiscountPolicy);
    }

    @Test
    void testDiscountPolicyApplies10PercentDiscount() {
        // The DiscountConfig creates a PercentageDiscountPolicy with 10% discount
        assertEquals(90, discountPolicy.apply(100));
        assertEquals(180, discountPolicy.apply(200));
        assertEquals(900, discountPolicy.apply(1000));
    }

    @Test
    void testDiscountPolicyWithZeroPrice() {
        assertEquals(0, discountPolicy.apply(0));
    }

    @Test
    void testPercentageDiscountPolicyDirectly() {
        PercentageDiscountPolicy policy = new PercentageDiscountPolicy(10);
        assertEquals(90, policy.apply(100));
    }

    @Test
    void testPercentageDiscountPolicyWithDifferentPercent() {
        PercentageDiscountPolicy policy = new PercentageDiscountPolicy(50);
        assertEquals(50, policy.apply(100));
        assertEquals(100, policy.apply(200));
    }
}
