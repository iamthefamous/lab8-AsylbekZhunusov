package com.example.lab8week.ex3.repository;

import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryProductRepository {

    private final Map<Long, String> products = new HashMap<>();

    public InMemoryProductRepository() {
        products.put(1L, "Laptop");
        products.put(2L, "Mouse");
    }

    public String findNameById(Long id) {
        return products.get(id);
    }
}
