package com.food.recipe.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by Semih, 1.10.2023
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SocialApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(SocialApiApplication.class, args);
    }
}