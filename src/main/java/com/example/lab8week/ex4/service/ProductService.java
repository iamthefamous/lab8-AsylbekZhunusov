package com.example.lab8week.ex4.service;

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

    // New method: apply discount to a hypothetical price
    public int getDiscountedPrice(int price) {
        return discountPolicy.apply(price);
    }
}
