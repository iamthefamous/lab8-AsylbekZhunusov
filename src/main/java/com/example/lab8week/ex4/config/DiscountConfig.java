package com.example.lab8week.ex4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiscountConfig {

    @Bean
    public DiscountPolicy discountPolicy() {
        return new PercentageDiscountPolicy(10);
    }
}
