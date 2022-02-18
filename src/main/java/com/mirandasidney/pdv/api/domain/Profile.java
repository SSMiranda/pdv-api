package com.mirandasidney.pdv.api.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static java.util.UUID.randomUUID;

@Entity
@NoArgsConstructor
@Table(name = "PROFILE")
public class Profile implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Getter
    @Column(name = "PROFILE_ID", updatable = false, unique = true, nullable = false, columnDefinition = "uuid")
    private UUID uuid = randomUUID();

    @Getter
    @Setter
    @Column(unique = true)
    private String profileName;

    @Getter
    @Setter
    @Column
    private String description;

    @Getter
    @Setter
    @OneToMany(mappedBy = "profile", fetch = FetchType.EAGER)
    private Set<User> users;

    @Getter
    @Setter
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "modules_profile",
            joinColumns = @JoinColumn(name = "PROFILE_ID"),
            inverseJoinColumns = @JoinColumn(name = "MODULE_ID"))
    private Set<Module> modules = new HashSet<>();

    public void appendModule(Module module) {
        this.modules.add(module);
    }

}
