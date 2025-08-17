package org.rahmasir.fmuserservice.dto;

import java.util.Set;
import java.util.UUID;

/**
 * DTO representing the public profile of a freelancer.
 *
 * @param id     The user's unique ID.
 * @param email  The user's email.
 * @param name   The freelancer's name.
 * @param bio    The freelancer's biography.
 * @param skills A set of skills the freelancer possesses.
 */
public record FreelancerProfileDto(
        UUID id,
        String email,
        String name,
        String bio,
        Set<SkillDto> skills
) {
}
