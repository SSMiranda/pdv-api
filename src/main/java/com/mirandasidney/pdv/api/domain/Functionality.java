package com.mirandasidney.pdv.api.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;

import static java.util.UUID.randomUUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Functionality {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FUNCTIONALITY_ID", updatable = false, unique = true, nullable = false, columnDefinition = "uuid")
    private UUID uuid;

    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private boolean canView;
    @Getter
    @Setter
    private boolean canEdit;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "MODULE_ID", nullable = false)
    private Module module;

}
