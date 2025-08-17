package org.rahmasir.fmuserservice.security;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Service interface for JSON Web Token (JWT) operations.
 * Defines the contract for generating, validating, and extracting information from JWTs.
 */
public interface JwtService {

    /**
     * Extracts the username (email) from a given JWT.
     *
     * @param token The JWT string.
     * @return The username contained within the token.
     */
    String extractUsername(String token);

    /**
     * Generates a new JWT for a given user.
     *
     * @param userDetails The user details for whom the token is to be generated.
     * @return The generated JWT string.
     */
    String generateToken(UserDetails userDetails);

    /**
     * Checks if a given JWT is valid.
     * A token is valid if it belongs to the user and has not expired.
     *
     * @param token       The JWT string.
     * @param userDetails The user details to validate against.
     * @return true if the token is valid, false otherwise.
     */
    boolean isTokenValid(String token, UserDetails userDetails);
}
