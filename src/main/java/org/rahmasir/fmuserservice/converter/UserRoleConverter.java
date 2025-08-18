package org.rahmasir.fmuserservice.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.rahmasir.fmsharedlib.enums.UserRole;

import java.util.stream.Stream;

/**
 * JPA Attribute Converter for the UserRole enum.
 * This converter allows storing the enum as a string in the database
 * and provides a centralized place for this logic.
 */
@Converter(autoApply = false) // Set to false to apply it only where explicitly declared with @Convert
public class UserRoleConverter implements AttributeConverter<UserRole, String> {

    /**
     * Converts the UserRole enum to its string representation for database storage.
     *
     * @param role the enum value to be converted
     * @return the string representation of the role, or null if the input is null
     */
    @Override
    public String convertToDatabaseColumn(UserRole role) {
        if (role == null) {
            return null;
        }
        return role.name();
    }

    /**
     * Converts the string from the database back to the UserRole enum.
     *
     * @param code the string value from the database
     * @return the corresponding UserRole enum, or null if the input is null or invalid
     */
    @Override
    public UserRole convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(UserRole.values())
                .filter(c -> c.name().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
