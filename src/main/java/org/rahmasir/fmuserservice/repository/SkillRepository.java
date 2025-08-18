package org.rahmasir.fmuserservice.repository;

import org.rahmasir.fmuserservice.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Spring Data JPA repository for the Skill entity.
 */
@Repository
public interface SkillRepository extends JpaRepository<Skill, UUID> {

    /**
     * Finds a skill by its name, ignoring case.
     * This is crucial for checking if a skill already exists before creating a new one.
     *
     * @param name The name of the skill to find.
     * @return an Optional containing the found Skill, or an empty Optional if not found.
     */
    Optional<Skill> findByNameIgnoreCase(String name);
}
