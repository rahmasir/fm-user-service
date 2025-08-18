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

    SkillDto toSkillDto(Skill skill);

    @Mapping(source = "freelancerProfile.name", target = "name")
    @Mapping(source = "freelancerProfile.bio", target = "bio")
    @Mapping(source = "freelancerProfile.skills", target = "skills")
    FreelancerProfileDto toFreelancerProfileDto(User user);

    @Mapping(source = "employerProfile.companyName", target = "companyName")
    @Mapping(source = "employerProfile.bio", target = "bio")
    EmployerProfileDto toEmployerProfileDto(User user);

    /**
     * Updates an existing FreelancerProfile entity from an UpdateFreelancerProfileRequest DTO.
     * The 'skills' field is ignored because it requires special business logic (findOrCreate)
     * which is handled in the service layer. 'id' and 'user' are also ignored as they should
     * not be changed during an update.
     *
     * @param dto     The DTO containing the updated data.
     * @param profile The entity to be updated.
     */
    @Mapping(target = "skills", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    void updateFreelancerProfileFromDto(UpdateFreelancerProfileRequest dto, @MappingTarget FreelancerProfile profile);

    /**
     * Updates an existing EmployerProfile entity from an UpdateEmployerProfileRequest DTO.
     *
     * @param dto     The DTO containing the updated data.
     * @param profile The entity to be updated.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    void updateEmployerProfileFromDto(UpdateEmployerProfileRequest dto, @MappingTarget EmployerProfile profile);
}
