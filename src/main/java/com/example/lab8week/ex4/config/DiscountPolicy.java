package com.example.lab8week.ex4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public interface DiscountPolicy {
    int apply(int originalPrice);
}

