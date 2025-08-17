package org.rahmasir.fmuserservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.rahmasir.fmuserservice.dto.*;
import org.rahmasir.fmuserservice.entity.EmployerProfile;
import org.rahmasir.fmuserservice.entity.FreelancerProfile;
import org.rahmasir.fmuserservice.entity.Skill;
import org.rahmasir.fmuserservice.entity.User;

/**
 * Mapper for converting between user-related entities and DTOs.
 * MapStruct will generate the implementation of this interface at compile time.
 */
@Mapper(componentModel = "spring") // Generates a Spring bean for dependency injection
public interface UserMapper {

    /**
     * Converts a Skill entity to a SkillDto.
     *
     * @param skill The Skill entity.
     * @return The corresponding SkillDto.
     */
    SkillDto toSkillDto(Skill skill);

    /**
     * Converts a User entity to a FreelancerProfileDto.
     * It maps fields from both the User and its associated FreelancerProfile.
     *
     * @param user The User entity, which must have a non-null freelancerProfile.
     * @return The corresponding FreelancerProfileDto.
     */
    @Mapping(source = "freelancerProfile.name", target = "name")
    @Mapping(source = "freelancerProfile.bio", target = "bio")
    @Mapping(source = "freelancerProfile.skills", target = "skills")
    FreelancerProfileDto toFreelancerProfileDto(User user);

    /**
     * Converts a User entity to an EmployerProfileDto.
     * It maps fields from both the User and its associated EmployerProfile.
     *
     * @param user The User entity, which must have a non-null employerProfile.
     * @return The corresponding EmployerProfileDto.
     */
    @Mapping(source = "employerProfile.companyName", target = "companyName")
    @Mapping(source = "employerProfile.bio", target = "bio")
    EmployerProfileDto toEmployerProfileDto(User user);

    /**
     * Updates an existing FreelancerProfile entity from an UpdateFreelancerProfileRequest DTO.
     * The @MappingTarget annotation tells MapStruct to update the provided 'profile' object
     * instead of creating a new one.
     *
     * @param dto     The DTO containing the updated data.
     * @param profile The entity to be updated.
     */
    void updateFreelancerProfileFromDto(UpdateFreelancerProfileRequest dto, @MappingTarget FreelancerProfile profile);

    /**
     * Updates an existing EmployerProfile entity from an UpdateEmployerProfileRequest DTO.
     *
     * @param dto     The DTO containing the updated data.
     * @param profile The entity to be updated.
     */
    void updateEmployerProfileFromDto(UpdateEmployerProfileRequest dto, @MappingTarget EmployerProfile profile);
}
