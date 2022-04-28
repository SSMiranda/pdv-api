package com.mirandasidney.pdv.api.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "PROFILE")
public class Profile implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Column(name = "PROFILE_ID", updatable = false, unique = true, nullable = false, columnDefinition = "uuid")
    private UUID uuid;

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
    @OneToMany(mappedBy = "profile")
    private Set<User> users;

    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "modules_profile",
            joinColumns = @JoinColumn(name = "PROFILE_ID"),
            inverseJoinColumns = @JoinColumn(name = "MODULE_ID"))
    private Set<Module> modules;

    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "functionality_profile",
            joinColumns = @JoinColumn(name = "PROFILE_ID"),
            inverseJoinColumns = @JoinColumn(name = "FUNCTIONALITY_ID"))
    private Set<Functionality> functionalities;

    public void appendModule(Module module) {
        this.modules.add(module);
    }

    public void appendFunctionality(Functionality functionality) {
        this.functionalities.add(functionality);
    }

}
