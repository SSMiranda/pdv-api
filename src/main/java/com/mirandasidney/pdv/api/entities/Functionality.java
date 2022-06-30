package com.mirandasidney.pdv.api.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Functionality implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FUNCTIONALITY_ID", updatable = false, unique = true, nullable = false)
    private UUID uuid;

    @Getter
    @Setter
    @Column(unique = true)
    private String name;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private boolean visible;

//    @Getter
//    @Setter
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Module module;

}
