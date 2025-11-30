package com.example.lab8week.ex3.service;

import com.example.lab8week.ex3.Config.DiscountPolicy;
import com.example.lab8week.ex3.repository.InMemoryProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final InMemoryProductRepository productRepository;
    private final DiscountPolicy discountPolicy;

    public ProductService(InMemoryProductRepository productRepository,
                          DiscountPolicy discountPolicy) {
        this.productRepository = productRepository;
        this.discountPolicy = discountPolicy;
    }

    public String getProductName(Long id) {
        return productRepository.findNameById(id);
    }

    // Apply a discount to hypothetical product price
    public int getDiscountedPrice(int originalPrice) {
        return discountPolicy.apply(originalPrice);
    }
}
