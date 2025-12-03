package com.example.lab8week;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(
    basePackages = "com.example.lab8week",
    excludeFilters = @ComponentScan.Filter(
        type = FilterType.REGEX,
        pattern = "com\\.example\\.lab8week\\.ex.*"
    )
)
public class Lab8weekApplication {

    public static void main(String[] args) {
        SpringApplication.run(Lab8weekApplication.class, args);
    }

}
