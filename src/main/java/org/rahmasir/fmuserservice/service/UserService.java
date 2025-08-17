package org.rahmasir.fmuserservice.service;

import org.rahmasir.fmuserservice.dto.*;

import java.util.UUID;

/**
 * Service interface for user-related business logic.
 */
public interface UserService {

    /**
     * Registers a new freelancer.
     * @param request The registration data.
     * @return A DTO of the newly created freelancer's profile.
     */
    FreelancerProfileDto registerFreelancer(FreelancerRegisterRequest request);

    /**
     * Registers a new employer.
     * @param request The registration data.
     * @return A DTO of the newly created employer's profile.
     */
    EmployerProfileDto registerEmployer(EmployerRegisterRequest request);

    FreelancerProfileDto getFreelancerProfile(UUID userId);

    EmployerProfileDto getEmployerProfile(UUID userId);

    FreelancerProfileDto updateFreelancerProfile(UUID userId, UpdateFreelancerProfileRequest request);

    EmployerProfileDto updateEmployerProfile(UUID userId, UpdateEmployerProfileRequest request);
}
