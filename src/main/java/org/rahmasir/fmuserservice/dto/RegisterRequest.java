package org.rahmasir.fmuserservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.rahmasir.fmsharedlib.enums.UserRole;

/**
 * DTO for user registration. It captures all necessary information
 * to create a new user and their initial profile.
 *
 * @param email       The user's email address. Must be a valid format.
 * @param password    The user's password. Must be at least 8 characters long.
 * @param role        The role of the user (FREELANCER or EMPLOYER).
 * @param name        The freelancer's name (required if role is FREELANCER).
 * @param companyName The employer's company name (required if role is EMPLOYER).
 */
public record RegisterRequest(
        @NotBlank(message = "Email is required")
        @Email(message = "Email should be valid")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 8, message = "Password must be at least 8 characters long")
        String password,

        @NotNull(message = "User role is required")
        UserRole role,

        String name,

        String companyName
) {
}
