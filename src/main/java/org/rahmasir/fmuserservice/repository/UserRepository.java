package org.rahmasir.fmuserservice.repository;

import org.rahmasir.fmuserservice.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Spring Data JPA repository for the User entity.
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    /**
     * Finds a user by their email address.
     * This is essential for the login process and for checking if an email is already registered.
     *
     * @param email The email address to search for.
     * @return an Optional containing the found User, or an empty Optional if not found.
     */
    Optional<User> findByEmail(String email);

    /**
     * Checks if a user with the given email address exists.
     * This is a more performant way to check for email existence than findByEmail().isPresent().
     *
     * @param email The email address to check.
     * @return true if a user with the email exists, false otherwise.
     */
    boolean existsByEmail(String email);

    /**
     * Finds a user by their ID and eagerly fetches their freelancer profile and associated skills.
     * This avoids the N+1 query problem when accessing skills.
     *
     * @param id The UUID of the user to find.
     * @return an Optional containing the User with their profile and skills, or empty if not found.
     */
    @EntityGraph(attributePaths = {"freelancerProfile", "freelancerProfile.skills"})
    Optional<User> findById(UUID id);
}
