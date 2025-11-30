package com.example.lab8week.ex3.controller;

import com.example.lab8week.ex3.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public String getProduct(@PathVariable Long id) {
        return productService.getProductName(id);
    }

    @GetMapping("/{id}/discounted-price")
    public int getDiscountedPrice(@PathVariable int originalPrice) {
        return productService.getDiscountedPrice(originalPrice);
    }
}
