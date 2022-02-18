package com.mirandasidney.pdv.api.repository;

import com.mirandasidney.pdv.api.domain.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface ModuleRepository extends JpaRepository<Module, UUID> {

    default Set<Module> findAllSet() {
        return new HashSet<>(this.findAll());
    }

    Optional<Module> findByName(String name);
}
