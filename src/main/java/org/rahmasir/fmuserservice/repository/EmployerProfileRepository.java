package org.rahmasir.fmuserservice.repository;

import org.rahmasir.fmuserservice.entity.EmployerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Spring Data JPA repository for the EmployerProfile entity.
 */
@Repository
public interface EmployerProfileRepository extends JpaRepository<EmployerProfile, UUID> {
}
