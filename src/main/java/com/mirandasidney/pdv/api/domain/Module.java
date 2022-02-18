package com.mirandasidney.pdv.api.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static java.util.UUID.randomUUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Module {

    @Id
    @GeneratedValue
    @Getter
    @Column(name = "MODULE_ID", updatable = false, unique = true, nullable = false, columnDefinition = "uuid")
    private UUID uuid = randomUUID();

    @Getter
    @Setter
    private boolean enable;

    @Getter
    @Setter
    @Column(unique = true)
    private String name;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "modules")
    private Set<Profile> profiles = new HashSet<>();
}
