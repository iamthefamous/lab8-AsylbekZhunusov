package com.example.lab8week.ex6.component;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Scope("singleton") // change to "prototype" to test prototype behavior
public class IdBean {

    private final String id = UUID.randomUUID().toString();

    public String getId() {
        return id;
    }
}
