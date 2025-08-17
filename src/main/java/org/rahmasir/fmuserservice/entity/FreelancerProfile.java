package org.rahmasir.fmuserservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Contains profile information specific to a user with the FREELANCER role.
 */
@Entity
@Table(name = "freelancer_profile")
@Getter
@Setter
@ToString(exclude = "user")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class FreelancerProfile {

    @Id
    private UUID id; // The primary key will be the same as the User's ID

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId // This maps the 'id' field as both primary key and foreign key
    @JoinColumn(name = "id")
    private User user;

    @Column(nullable = false)
    private String name;

    private String bio;

    /**
     * A many-to-many relationship with the Skill entity.
     * FetchType.LAZY is used to prevent skills from being loaded automatically with the profile,
     * which improves performance. They must be fetched explicitly when needed.
     * CascadeType.PERSIST and MERGE are used so that if we add a new, unsaved Skill
     * to a FreelancerProfile and save the profile, the new Skill will also be persisted.
     */
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "freelancer_skill_association",
            joinColumns = @JoinColumn(name = "freelancer_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<Skill> skills = new HashSet<>();

    public FreelancerProfile(User user, String name) {
        this.user = user;
        this.name = name;
    }
}
