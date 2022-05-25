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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Column(name = "role_id", updatable = false, unique = true, nullable = false, columnDefinition = "uuid")
    private UUID uuid;

    @Getter
    @Setter
    @Column(unique = true)
    private String profileName;

    @Getter
    @Setter
    @Column
    private String description;

//    @Getter
//    @Setter
//    @OneToMany(mappedBy = "roles")
//    private Set<User> users;

//    @Getter
//    @Setter
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "modules_profile",
//            joinColumns = @JoinColumn(name = "PROFILE_ID"),
//            inverseJoinColumns = @JoinColumn(name = "MODULE_ID"))
//    private Set<Module> modules;

//    @Getter
//    @Setter
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "functionality_profile",
//            joinColumns = @JoinColumn(name = "PROFILE_ID"),
//            inverseJoinColumns = @JoinColumn(name = "FUNCTIONALITY_ID"))
//    private Set<Functionality> functionalities;

    @Override
    public String getAuthority() {
        return null;
    }

//    public void appendModule(Module module) {
//        this.modules.add(module);
//    }

//    public void appendFunctionality(Functionality functionality) {
//        this.functionalities.add(functionality);
//    }

}
