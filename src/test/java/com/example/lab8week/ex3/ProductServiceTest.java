package com.example.lab8week.ex3;

import com.example.lab8week.ex3.Config.DiscountPolicy;
import com.example.lab8week.ex3.Config.PercentageDiscountPolicy;
import com.example.lab8week.ex3.repository.InMemoryProductRepository;
import com.example.lab8week.ex3.service.ProductService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for Layered Architecture example (ex3).
 * Unit tests that don't require Spring context.
 */
class ProductServiceTest {

    @Test
    void testInMemoryProductRepository() {
        InMemoryProductRepository repository = new InMemoryProductRepository();
        
        assertEquals("Laptop", repository.findNameById(1L));
        assertEquals("Mouse", repository.findNameById(2L));
        assertNull(repository.findNameById(999L));
    }

    @Test
    void testPercentageDiscountPolicy() {
        PercentageDiscountPolicy policy = new PercentageDiscountPolicy(10);
        
        assertEquals(90, policy.apply(100));
        assertEquals(180, policy.apply(200));
        assertEquals(0, policy.apply(0));
    }

    @Test
    void testPercentageDiscountPolicy20Percent() {
        PercentageDiscountPolicy policy = new PercentageDiscountPolicy(20);
        
        assertEquals(80, policy.apply(100));
        assertEquals(160, policy.apply(200));
    }

    @Test
    void testPercentageDiscountPolicyZeroPercent() {
        PercentageDiscountPolicy policy = new PercentageDiscountPolicy(0);
        
        assertEquals(100, policy.apply(100));
        assertEquals(200, policy.apply(200));
    }

    @Test
    void testProductService() {
        InMemoryProductRepository repository = new InMemoryProductRepository();
        DiscountPolicy discountPolicy = new PercentageDiscountPolicy(10);
        ProductService service = new ProductService(repository, discountPolicy);
        
        assertEquals("Laptop", service.getProductName(1L));
        assertEquals("Mouse", service.getProductName(2L));
        assertEquals(90, service.getDiscountedPrice(100));
    }

    @Test
    void testProductServiceWithDifferentDiscounts() {
        InMemoryProductRepository repository = new InMemoryProductRepository();
        DiscountPolicy discountPolicy = new PercentageDiscountPolicy(25);
        ProductService service = new ProductService(repository, discountPolicy);
        
        assertEquals(75, service.getDiscountedPrice(100));
        assertEquals(750, service.getDiscountedPrice(1000));
    }

    @Test
    void testDiscountPolicyInterface() {
        DiscountPolicy policy = new PercentageDiscountPolicy(15);
        
        assertNotNull(policy);
        assertTrue(policy instanceof DiscountPolicy);
    }
}
