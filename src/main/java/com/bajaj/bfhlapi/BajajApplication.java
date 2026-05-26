package com.bajaj.bfhlapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BajajApplication {

    public static void main(String[] args) {
        System.out.println("Starting Bajaj BFHL API...");
        SpringApplication.run(BajajApplication.class, args);
        System.out.println("Bajaj BFHL API started successfully!");
    }
}
