package org.rahmasir.fmuserservice.repository;

import org.rahmasir.fmuserservice.entity.FreelancerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Spring Data JPA repository for the FreelancerProfile entity.
 */
@Repository
public interface FreelancerProfileRepository extends JpaRepository<FreelancerProfile, UUID> {
}
