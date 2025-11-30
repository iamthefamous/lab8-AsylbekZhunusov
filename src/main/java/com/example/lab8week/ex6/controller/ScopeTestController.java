package com.example.lab8week.ex6.controller;

import com.example.lab8week.ex6.service.ServiceA;
import com.example.lab8week.ex6.service.ServiceB;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ScopeTestController {

    private final ServiceA serviceA;
    private final ServiceB serviceB;

    public ScopeTestController(ServiceA serviceA, ServiceB serviceB) {
        this.serviceA = serviceA;
        this.serviceB = serviceB;
    }

    @GetMapping("/ids")
    public List<String> ids() {
        return List.of(serviceA.getId(), serviceB.getId());
    }
}
