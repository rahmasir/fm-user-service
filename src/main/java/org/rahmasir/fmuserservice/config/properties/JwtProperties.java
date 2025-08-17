package org.rahmasir.fmuserservice.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * A type-safe configuration properties record for JWT settings.
 * This class maps properties prefixed with "jwt" from the application.yml file.
 *
 * @param secret       The secret key used for signing the JWT.
 * @param expirationMs The expiration time for the JWT in milliseconds.
 */
@ConfigurationProperties(prefix = "jwt")
public record JwtProperties(String secret, long expirationMs) {
}
