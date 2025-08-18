package org.rahmasir.fmuserservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.rahmasir.fmsharedlib.enums.UserRole;
import org.rahmasir.fmuserservice.dto.EmployerProfileDto;
import org.rahmasir.fmuserservice.dto.FreelancerProfileDto;
import org.rahmasir.fmuserservice.dto.UpdateEmployerProfileRequest;
import org.rahmasir.fmuserservice.dto.UpdateFreelancerProfileRequest;
import org.rahmasir.fmuserservice.entity.User;
import org.rahmasir.fmuserservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Controller for managing user profiles.
 * These endpoints require authentication.
 */
@RestController
@RequestMapping("/api/profiles")
@RequiredArgsConstructor
@Tag(name = "User Profiles", description = "APIs for fetching and updating user profiles")
@SecurityRequirement(name = "bearerAuth") // Indicates that endpoints here require JWT auth
public class ProfileController {

    private final UserService userService;

    @Operation(summary = "Get the current user's profile", description = "Retrieves the profile of the currently authenticated user.")
    @GetMapping("/me")
    public ResponseEntity<?> getMyProfile(@AuthenticationPrincipal User currentUser) {
        if (currentUser.getRole() == UserRole.FREELANCER) {
            return ResponseEntity.ok(userService.getFreelancerProfile(currentUser.getId()));
        } else if (currentUser.getRole() == UserRole.EMPLOYER) {
            return ResponseEntity.ok(userService.getEmployerProfile(currentUser.getId()));
        }
        // This case should ideally not be reached if roles are handled correctly
        return ResponseEntity.badRequest().body("User has an invalid role.");
    }

    @Operation(summary = "Get a freelancer's profile by ID")
    @GetMapping("/freelancer/{id}")
    public ResponseEntity<FreelancerProfileDto> getFreelancerProfile(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getFreelancerProfile(id));
    }

    @Operation(summary = "Get an employer's profile by ID")
    @GetMapping("/employer/{id}")
    public ResponseEntity<EmployerProfileDto> getEmployerProfile(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getEmployerProfile(id));
    }

    @Operation(summary = "Update the current freelancer's profile", description = "Updates the profile of the currently authenticated freelancer.")
    @PutMapping("/freelancer")
    public ResponseEntity<FreelancerProfileDto> updateFreelancerProfile(@AuthenticationPrincipal User currentUser, @Valid @RequestBody UpdateFreelancerProfileRequest request) {
        return ResponseEntity.ok(userService.updateFreelancerProfile(currentUser.getId(), request));
    }

    @Operation(summary = "Update the current employer's profile", description = "Updates the profile of the currently authenticated employer.")
    @PutMapping("/employer")
    public ResponseEntity<EmployerProfileDto> updateEmployerProfile(@AuthenticationPrincipal User currentUser, @Valid @RequestBody UpdateEmployerProfileRequest request) {
        return ResponseEntity.ok(userService.updateEmployerProfile(currentUser.getId(), request));
    }
}
