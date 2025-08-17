package org.rahmasir.fmuserservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Main entry point for the User Service application.
 * This service is responsible for managing users, authentication, and profiles.
 */
@SpringBootApplication
@EnableCaching // Enables Spring's caching abstraction
public class FmUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FmUserServiceApplication.class, args);
    }

}
