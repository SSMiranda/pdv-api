package com.mirandasidney.pdv.api.domain;

import lombok.Getter;
import lombok.Setter;
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
@Table(name = "role")
public class Role implements GrantedAuthority {
    private static final long serialVersionUID = 1L;

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, unique = true, nullable = false)
    private UUID uuid;

    @Setter
    @Column(nullable = false)
    private String name;

    @Getter
    @Setter
    private String description;

    @Override
    public String getAuthority() {
        return this.name;
    }

}
