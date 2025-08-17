package org.rahmasir.fmuserservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.rahmasir.fmsharedlib.enums.UserRole;
import org.rahmasir.fmuserservice.dto.*;
import org.rahmasir.fmuserservice.entity.EmployerProfile;
import org.rahmasir.fmuserservice.entity.FreelancerProfile;
import org.rahmasir.fmuserservice.entity.User;
import org.rahmasir.fmuserservice.mapper.UserMapper;
import org.rahmasir.fmuserservice.repository.EmployerProfileRepository;
import org.rahmasir.fmuserservice.repository.FreelancerProfileRepository;
import org.rahmasir.fmuserservice.repository.UserRepository;
import org.rahmasir.fmuserservice.service.SkillService;
import org.rahmasir.fmuserservice.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Implementation of the UserService interface.
 * Contains the business logic for user registration and profile management.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final FreelancerProfileRepository freelancerProfileRepository;
    private final EmployerProfileRepository employerProfileRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final SkillService skillService;

    @Override
    @Transactional
    public User register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            // In a real application, throw a custom exception
            throw new IllegalArgumentException("Email is already in use");
        }

        var user = new User(
                request.email(),
                passwordEncoder.encode(request.password()),
                request.role()
        );

        if (request.role() == UserRole.FREELANCER) {
            // handle here to avoid duplicating of extra DTO for each type of user
            if (request.name() == null || request.name().isBlank()) {
                throw new IllegalArgumentException("Freelancer name is required");
            }
            FreelancerProfile profile = new FreelancerProfile(user, request.name());
            user.setFreelancerProfile(profile);
        } else if (request.role() == UserRole.EMPLOYER) {
            if (request.companyName() == null || request.companyName().isBlank()) {
                throw new IllegalArgumentException("Company name is required");
            }
            EmployerProfile profile = new EmployerProfile(user, request.companyName());
            user.setEmployerProfile(profile);
        }

        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public FreelancerProfileDto getFreelancerProfile(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found")); // Replace with custom exception
        if (user.getRole() != UserRole.FREELANCER) {
            throw new IllegalArgumentException("User is not a freelancer");
        }
        // Eagerly fetch skills within the transaction
        user.getFreelancerProfile().getSkills().size();
        return userMapper.toFreelancerProfileDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public EmployerProfileDto getEmployerProfile(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (user.getRole() != UserRole.EMPLOYER) {
            throw new IllegalArgumentException("User is not an employer");
        }
        return userMapper.toEmployerProfileDto(user);
    }

    @Override
    @Transactional
    public FreelancerProfileDto updateFreelancerProfile(UUID userId, UpdateFreelancerProfileRequest request) {
        FreelancerProfile profile = freelancerProfileRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        userMapper.updateFreelancerProfileFromDto(request, profile);
        profile.setSkills(skillService.findOrCreateSkills(request.skills()));

        FreelancerProfile updatedProfile = freelancerProfileRepository.save(profile);
        // Eagerly fetch skills to return in the DTO
        updatedProfile.getSkills().size();
        return userMapper.toFreelancerProfileDto(updatedProfile.getUser());
    }

    @Override
    @Transactional
    public EmployerProfileDto updateEmployerProfile(UUID userId, UpdateEmployerProfileRequest request) {
        EmployerProfile profile = employerProfileRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        userMapper.updateEmployerProfileFromDto(request, profile);
        EmployerProfile updatedProfile = employerProfileRepository.save(profile);
        return userMapper.toEmployerProfileDto(updatedProfile.getUser());
    }
}
