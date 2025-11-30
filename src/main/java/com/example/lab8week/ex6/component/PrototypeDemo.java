package com.example.lab8week.ex6.component;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class PrototypeDemo implements CommandLineRunner {

    private final ApplicationContext context;

    public PrototypeDemo(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void run(String... args) throws Exception {
        IdBean id1 = context.getBean(IdBean.class);
        IdBean id2 = context.getBean(IdBean.class);

        System.out.println("Prototype bean 1: " + id1.getId());
        System.out.println("Prototype bean 2: " + id2.getId());
    }
}
