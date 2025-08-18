package org.rahmasir.fmuserservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.rahmasir.fmuserservice.dto.*;
import org.rahmasir.fmuserservice.security.JwtService;
import org.rahmasir.fmuserservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling user authentication and registration endpoints.
 * These endpoints are publicly accessible.
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "APIs for User Registration and Login")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Operation(summary = "Register a new freelancer", description = "Creates a new user with the FREELANCER role and an associated profile.")
    @ApiResponse(responseCode = "201", description = "Freelancer registered successfully")
    @ApiResponse(responseCode = "409", description = "Email already in use")
    @PostMapping("/register/freelancer")
    public ResponseEntity<FreelancerProfileDto> registerFreelancer(@Valid @RequestBody FreelancerRegisterRequest request) {
        FreelancerProfileDto profile = userService.registerFreelancer(request);
        return new ResponseEntity<>(profile, HttpStatus.CREATED);
    }

    @Operation(summary = "Register a new employer", description = "Creates a new user with the EMPLOYER role and an associated profile.")
    @ApiResponse(responseCode = "201", description = "Employer registered successfully")
    @ApiResponse(responseCode = "409", description = "Email already in use")
    @PostMapping("/register/employer")
    public ResponseEntity<EmployerProfileDto> registerEmployer(@Valid @RequestBody EmployerRegisterRequest request) {
        EmployerProfileDto profile = userService.registerEmployer(request);
        return new ResponseEntity<>(profile, HttpStatus.CREATED);
    }

    @Operation(summary = "Authenticate a user", description = "Authenticates a user with email and password, and returns a JWT access token upon success.")
    @ApiResponse(responseCode = "200", description = "Authentication successful")
    @ApiResponse(responseCode = "401", description = "Invalid credentials")
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@Valid @RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password()));

        var userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtService.generateToken(userDetails);
        return ResponseEntity.ok(new JwtAuthResponse(token));
    }
}
