package com.mirandasidney.pdv.api.repository;

import com.mirandasidney.pdv.api.entities.Functionality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Repository
public interface FunctionalityRepository extends JpaRepository<Functionality, UUID> {

    default Set<Functionality> findAllSet() {
        return new HashSet<>(this.findAll());
    }
}
