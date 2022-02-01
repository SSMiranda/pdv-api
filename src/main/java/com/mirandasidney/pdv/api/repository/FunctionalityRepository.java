package com.mirandasidney.pdv.api.repository;

import com.mirandasidney.pdv.api.domain.Functionality;
import com.mirandasidney.pdv.api.domain.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashSet;
import java.util.Set;

public interface FunctionalityRepository extends JpaRepository<Functionality, Long> {

    default Set<Functionality> findAllSet() {
        return new HashSet<>(this.findAll());
    }
}
