package org.rahmasir.fmuserservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Represents a unique skill that can be associated with freelancers or projects.
 */
@Entity
@Table(name = "skill")
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "name") // Skills are unique by name
@NoArgsConstructor
@AllArgsConstructor
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "skills")
    @ToString.Exclude // Avoid circular dependency in toString
    private Set<FreelancerProfile> freelancers = new HashSet<>();

    public Skill(String name) {
        this.name = name;
    }
}
