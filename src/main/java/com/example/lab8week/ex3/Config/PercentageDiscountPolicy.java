package com.example.lab8week.ex3.Config;

public class PercentageDiscountPolicy implements DiscountPolicy {
    private final int percent;

    public PercentageDiscountPolicy(int percent) {
        this.percent = percent;
    }

    @Override
    public int apply(int originalPrice) {
        return originalPrice * (100 - percent) / 100;
    }
}
