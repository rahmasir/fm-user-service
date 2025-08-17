package org.rahmasir.fmuserservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.Set;

/**
 * DTO for updating a freelancer's profile.
 *
 * @param name   The freelancer's updated name.
 * @param bio    The freelancer's updated biography.
 * @param skills The complete, updated set of skill names for the freelancer.
 */
public record UpdateFreelancerProfileRequest(
        @NotBlank(message = "Name cannot be blank")
        String name,
        String bio,
        @NotEmpty(message = "Skills list cannot be empty")
        Set<String> skills
) {
}
