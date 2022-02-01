package com.mirandasidney.pdv.api.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@NoArgsConstructor
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MODULE_ID")
    @Getter
    private Long id;
    @Getter
    @Setter
    private boolean isChecked;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String description;

    @OneToMany(mappedBy = "module")
    @Getter
    @Setter
    private Set<Functionality> functionalities;

}
