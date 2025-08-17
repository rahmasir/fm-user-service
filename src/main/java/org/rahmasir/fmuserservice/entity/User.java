package org.rahmasir.fmuserservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.rahmasir.fmsharedlib.enums.UserRole;
import org.rahmasir.fmuserservice.converter.UserRoleConverter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Represents a user in the system. This is the central entity for authentication
 * and holds the common information for all user types.
 */
@Entity
@Table(name = "app_user") // Using "app_user" as "user" is a reserved SQL keyword
@Getter
@Setter
@ToString(exclude = {"freelancerProfile", "employerProfile"}) // Avoid circular dependency in toString
@EqualsAndHashCode(of = "id") // Base equality on ID only
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Convert(converter = UserRoleConverter.class)
    @Column(nullable = false)
    private UserRole role;

    // One-to-one relationship with FreelancerProfile
    // CascadeType.ALL ensures that if a User is deleted, their profile is also deleted.
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private FreelancerProfile freelancerProfile;

    // One-to-one relationship with EmployerProfile
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private EmployerProfile employerProfile;

    public User(String email, String password, UserRole role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // --- UserDetails Implementation ---

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // The role is prefixed with "ROLE_" as per Spring Security's convention.
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getUsername() {
        // We use email as the username for authentication.
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
