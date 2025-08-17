package org.rahmasir.fmuserservice.service;

import org.rahmasir.fmuserservice.dto.EmployerProfileDto;
import org.rahmasir.fmuserservice.dto.FreelancerProfileDto;
import org.rahmasir.fmuserservice.dto.RegisterRequest;
import org.rahmasir.fmuserservice.dto.UpdateEmployerProfileRequest;
import org.rahmasir.fmuserservice.dto.UpdateFreelancerProfileRequest;
import org.rahmasir.fmuserservice.entity.User;

import java.util.UUID;

/**
 * Service interface for user-related business logic.
 * Defines the contract for user registration and profile management.
 */
public interface UserService {

    /**
     * Registers a new user in the system based on the provided request.
     *
     * @param request The registration request containing user details.
     * @return The newly created User entity.
     */
    User register(RegisterRequest request);

    /**
     * Retrieves the profile of a freelancer by their user ID.
     *
     * @param userId The UUID of the user.
     * @return A DTO representing the freelancer's profile.
     */
    FreelancerProfileDto getFreelancerProfile(UUID userId);

    /**
     * Retrieves the profile of an employer by their user ID.
     *
     * @param userId The UUID of the user.
     * @return A DTO representing the employer's profile.
     */
    EmployerProfileDto getEmployerProfile(UUID userId);

    /**
     * Updates the profile of an existing freelancer.
     *
     * @param userId The UUID of the user whose profile is to be updated.
     * @param request The DTO containing the updated profile information.
     * @return A DTO representing the updated freelancer profile.
     */
    FreelancerProfileDto updateFreelancerProfile(UUID userId, UpdateFreelancerProfileRequest request);

    /**
     * Updates the profile of an existing employer.
     *
     * @param userId The UUID of the user whose profile is to be updated.
     * @param request The DTO containing the updated profile information.
     * @return A DTO representing the updated employer profile.
     */
    EmployerProfileDto updateEmployerProfile(UUID userId, UpdateEmployerProfileRequest request);
}
