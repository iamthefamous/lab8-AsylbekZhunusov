package com.example.lab8week.ex4.controller;

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

    // Optional: endpoint for discount
    @GetMapping("/{id}/discounted")
    public String getDiscountedPrice(@PathVariable Long id,
                                     @RequestParam int price) {

        int finalPrice = productService.getDiscountedPrice(price);

        return "Product: " + productService.getProductName(id)
                + ", original price = " + price
                + ", discounted price = " + finalPrice;
    }
}
