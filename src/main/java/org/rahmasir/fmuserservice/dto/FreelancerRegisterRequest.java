package org.rahmasir.fmuserservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO specifically for registering a new Freelancer.
 *
 * @param email    The user's email address.
 * @param password The user's password (must be at least 8 characters).
 * @param name     The freelancer's full name.
 */
public record FreelancerRegisterRequest(
        @NotBlank(message = "Email is required")
        @Email(message = "Email should be valid")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 8, message = "Password must be at least 8 characters long")
        String password,

        @NotBlank(message = "Name is required")
        String name
) {
}
