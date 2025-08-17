package org.rahmasir.fmuserservice.dto;

import java.util.UUID;

/**
 * A simple DTO to represent a Skill.
 *
 * @param id   The unique identifier of the skill.
 * @param name The name of the skill (e.g., "Java").
 */
public record SkillDto(UUID id, String name) {
}
