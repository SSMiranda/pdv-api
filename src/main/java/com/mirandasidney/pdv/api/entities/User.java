package com.mirandasidney.pdv.api.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mirandasidney.pdv.api.utils.DateUtils;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author Sidney Miranda
 */

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "users",
        uniqueConstraints =
            @UniqueConstraint(columnNames = "username"))
public class User implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @Getter
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, unique = true, nullable = false)
    private UUID uuid;

    @Getter
    @NotBlank
    @Column(name = "FIRSTNAME", nullable = false)
    private String firstname;

    @Getter
    @NotBlank
    @Column(nullable = false)
    private String lastname;

    @Getter
    @NotBlank
    @Size(min = 3, max = 20)
    @Column(length = 20, nullable = false, unique = true)
    private String username;

    @Getter
    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 8, max = 120)
    @Column(nullable = false)
    private String password;

    @Getter
    @Size(max = 12)
    @Column(length = 12)
    private String phone;

    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_role",
            joinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "uuid",
                    foreignKey = @ForeignKey(name = "user_fk", value = ConstraintMode.CONSTRAINT)),
            inverseJoinColumns = @JoinColumn(
                name = "role_id",
                referencedColumnName = "uuid",
                    foreignKey = @ForeignKey(name = "role_fk", value = ConstraintMode.CONSTRAINT)))
    private Set<Role> roles = new HashSet<>();

    @Getter
    @Column(name = "CREATED_AT")
    private String createdAt = DateUtils.getDateTime();

    @Getter
    @Setter
    @Column(name = "UPDATED")
    private String updated;

    @Getter
    @Setter
    @Column(name = "STATUS")
    private Boolean active;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
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
