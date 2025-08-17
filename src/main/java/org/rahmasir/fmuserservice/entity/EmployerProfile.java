package org.rahmasir.fmuserservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

/**
 * Contains profile information specific to a user with the EMPLOYER role.
 */
@Entity
@Table(name = "employer_profile")
@Getter
@Setter
@ToString(exclude = "user")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class EmployerProfile {

    @Id
    private UUID id; // The primary key will be the same as the User's ID

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId // This maps the 'id' field as both primary key and foreign key
    @JoinColumn(name = "id")
    private User user;

    @Column(nullable = false)
    private String companyName;

    private String bio;

    public EmployerProfile(User user, String companyName) {
        this.user = user;
        this.companyName = companyName;
    }
}
