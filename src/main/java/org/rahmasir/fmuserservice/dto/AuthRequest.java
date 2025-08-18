package org.rahmasir.fmuserservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * DTO for user authentication (login).
 *
 * @param email    The user's email.
 * @param password The user's password.
 */
public record AuthRequest(
        @NotBlank(message = "Email is required")
        @Email(message = "Email should be valid")
        String email,

        @NotBlank(message = "Password is required")
        String password
) {
}
