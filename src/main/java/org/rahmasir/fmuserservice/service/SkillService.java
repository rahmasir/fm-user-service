package org.rahmasir.fmuserservice.service;

import org.rahmasir.fmuserservice.dto.SkillDto;
import org.rahmasir.fmuserservice.entity.Skill;

import java.util.List;
import java.util.Set;

/**
 * Service interface for skill-related business logic.
 */
public interface SkillService {

    /**
     * Retrieves all skills available in the system.
     *
     * @return A list of all skills as DTOs.
     */
    List<SkillDto> getAllSkills();

    /**
     * Finds or creates a set of skills based on their names.
     * For each name, it checks if the skill exists. If it does, it uses the existing one.
     * If not, it creates a new Skill entity.
     *
     * @param skillNames A set of strings representing the skill names.
     * @return A set of managed Skill entities.
     */
    Set<Skill> findOrCreateSkills(Set<String> skillNames);
}
