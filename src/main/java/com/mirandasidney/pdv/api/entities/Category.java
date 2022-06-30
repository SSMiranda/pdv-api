package com.mirandasidney.pdv.api.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CATEGORY_ID", updatable = false, unique = true, nullable = false)
    private UUID uuid;

    @Getter
    @Setter
    @Column(unique = true)
    private String name;

    @Getter
    @Setter
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private Set<Product> products = new HashSet<>();

}
