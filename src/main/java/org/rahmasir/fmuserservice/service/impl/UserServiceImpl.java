package org.rahmasir.fmuserservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.rahmasir.fmsharedlib.enums.UserRole;
import org.rahmasir.fmuserservice.dto.*;
import org.rahmasir.fmuserservice.entity.EmployerProfile;
import org.rahmasir.fmuserservice.entity.FreelancerProfile;
import org.rahmasir.fmuserservice.entity.User;
import org.rahmasir.fmuserservice.exception.CustomExceptions;
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
    public FreelancerProfileDto registerFreelancer(FreelancerRegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new CustomExceptions.EmailAlreadyExistsException("Email is already in use: " + request.email());
        }

        User user = new User(
                request.email(),
                passwordEncoder.encode(request.password()),
                UserRole.FREELANCER
        );
        var profile = new FreelancerProfile(user, request.name());
        user.setFreelancerProfile(profile);

        User savedUser = userRepository.save(user);
        return userMapper.toFreelancerProfileDto(savedUser);
    }

    @Override
    @Transactional
    public EmployerProfileDto registerEmployer(EmployerRegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new CustomExceptions.EmailAlreadyExistsException("Email is already in use: " + request.email());
        }

        User user = new User(
                request.email(),
                passwordEncoder.encode(request.password()),
                UserRole.EMPLOYER
        );
        var profile = new EmployerProfile(user, request.companyName());
        user.setEmployerProfile(profile);

        User savedUser = userRepository.save(user);
        return userMapper.toEmployerProfileDto(savedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public FreelancerProfileDto getFreelancerProfile(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomExceptions.ResourceNotFoundException("User not found with id: " + userId));
        if (user.getFreelancerProfile() == null) {
            throw new CustomExceptions.ResourceNotFoundException("Freelancer profile not found for user id: " + userId);
        }
        return userMapper.toFreelancerProfileDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public EmployerProfileDto getEmployerProfile(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomExceptions.ResourceNotFoundException("User not found with id: " + userId));
        if (user.getEmployerProfile() == null) {
            throw new CustomExceptions.ResourceNotFoundException("Employer profile not found for user id: " + userId);
        }
        return userMapper.toEmployerProfileDto(user);
    }

    @Override
    @Transactional
    public FreelancerProfileDto updateFreelancerProfile(UUID userId, UpdateFreelancerProfileRequest request) {
        FreelancerProfile profile = freelancerProfileRepository.findById(userId)
                .orElseThrow(() -> new CustomExceptions.ResourceNotFoundException("Freelancer profile not found for user id: " + userId));

        userMapper.updateFreelancerProfileFromDto(request, profile);
        profile.setSkills(skillService.findOrCreateSkills(request.skills()));

        freelancerProfileRepository.save(profile);
        return getFreelancerProfile(userId); // Re-fetch to get the DTO with updated skills
    }

    @Override
    @Transactional
    public EmployerProfileDto updateEmployerProfile(UUID userId, UpdateEmployerProfileRequest request) {
        EmployerProfile profile = employerProfileRepository.findById(userId)
                .orElseThrow(() -> new CustomExceptions.ResourceNotFoundException("Employer profile not found for user id: " + userId));

        userMapper.updateEmployerProfileFromDto(request, profile);
        EmployerProfile updatedProfile = employerProfileRepository.save(profile);
        return userMapper.toEmployerProfileDto(updatedProfile.getUser());
    }
}
