package org.rahmasir.fmuserservice.dto;

/**
 * DTO for the response after a successful authentication
 *
 * @param accessToken The JWT access token
 */
public record JwtAuthResponse(String accessToken) {
}
