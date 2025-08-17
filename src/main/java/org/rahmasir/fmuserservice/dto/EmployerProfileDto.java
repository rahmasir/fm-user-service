package org.rahmasir.userservice.dto;

import java.util.UUID;

/**
 * DTO representing the public profile of an employer.
 *
 * @param id          The user's unique ID.
 * @param email       The user's email.
 * @param companyName The employer's company name.
 * @param bio         The company's biography or description.
 */
public record EmployerProfileDto(
        UUID id,
        String email,
        String companyName,
        String bio
) {
}
