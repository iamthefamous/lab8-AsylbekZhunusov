package com.example.lab8week.ex7.controller;

import com.example.lab8week.ex7.component.RequestTrace;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestScopeController {

    private final RequestTrace requestTrace;

    public RequestScopeController(RequestTrace requestTrace) {
        this.requestTrace = requestTrace;
    }

    @GetMapping("/request-id")
    public String id() {
        return requestTrace.getRequestId();
    }
}
