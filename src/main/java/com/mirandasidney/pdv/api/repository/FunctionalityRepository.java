package com.mirandasidney.pdv.api.repository;

import com.mirandasidney.pdv.api.domain.Functionality;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public interface FunctionalityRepository extends JpaRepository<Functionality, UUID> {

    default Set<Functionality> findAllSet() {
        return new HashSet<>(this.findAll());
    }
}
