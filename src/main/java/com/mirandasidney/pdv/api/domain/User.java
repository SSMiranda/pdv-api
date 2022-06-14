package com.mirandasidney.pdv.api.domain;

import com.mirandasidney.pdv.api.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author Sidney Miranda
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Users")
public class User implements UserDetails {

    private static final long serialVersionUID = 1L;
    private static final Boolean STATUS = true;

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, unique = true, nullable = false)
    private UUID uuid;

    @Getter
    @Setter
    @Column(name = "FIRSTNAME", nullable = false)
    private String firstname;

    @Getter
    @Setter
    @Column(nullable = false)
    private String lastname;

    @Setter
    @Column(nullable = false)
    private String username;

    @Setter
    @Column(nullable = false)
    private String password;

    @Getter
    @Setter
    private String phone;

    @OneToMany(fetch = FetchType.EAGER)
    @Getter
    @JoinTable(name = "users_role",
            uniqueConstraints = @UniqueConstraint(
                    columnNames = {"user_id", "role_id"},
                    name = "unique_role_user"),
            joinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "uuid",
                    table = "user",
                    foreignKey = @ForeignKey(name = "user_fk", value = ConstraintMode.CONSTRAINT)),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id",
                    referencedColumnName = "uuid",
                    table = "role",
                    foreignKey = @ForeignKey(name = "role_fk", value = ConstraintMode.CONSTRAINT))
    )
    private Set<Role> roles = new HashSet<>();

    @Getter
    @Setter
    @Column(name = "CREATED_AT")
    private String createdAt = DateUtils.formatDate();

    @Getter
    @Setter
    @Column(name = "UPDATED")
    private String updated;

    @Getter
    @Setter
    @Column(name = "STATUS")
    private Boolean active = STATUS;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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

    public void addRole(Role... role) {
        Collections.addAll(this.roles, role);
    }
}
