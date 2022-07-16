package com.mirandasidney.pdv.api.entities;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

/**
 * @author Sidney Miranda
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "role")
public class Role implements GrantedAuthority {
    private static final long serialVersionUID = 1L;
    private static final String ROLE = "ROLE_";

    @Id
    @Getter
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, unique = true, nullable = false)
    private UUID uuid;

    @Column(nullable = false)
    @Getter
    private String name;

    @Getter
    @Setter
    private String description;

    @Override
    public String getAuthority() {
        return this.name;
    }

    public void setName(String name) {
        this.name = ROLE + name.toUpperCase();
    }
}
