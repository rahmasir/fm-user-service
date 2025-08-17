package org.rahmasir.fmuserservice;

import org.rahmasir.fmuserservice.config.properties.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Main entry point for the User Service application.
 * This service is responsible for managing users, authentication, and profiles.
 */
@SpringBootApplication
@EnableCaching // Enables Spring's caching abstraction
@EnableConfigurationProperties(JwtProperties.class) // Enables our custom JWT properties
public class FmUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FmUserServiceApplication.class, args);
    }

}
