package org.rahmasir.fmuserservice.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO for updating an employer's profile.
 *
 * @param companyName The employer's updated company name.
 * @param bio         The company's updated biography.
 */
public record UpdateEmployerProfileRequest(
        @NotBlank(message = "Company name cannot be blank")
        String companyName,
        String bio
) {
}
