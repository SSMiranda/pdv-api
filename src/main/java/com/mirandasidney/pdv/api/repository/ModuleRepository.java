package com.mirandasidney.pdv.api.repository;

import com.mirandasidney.pdv.api.domain.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashSet;
import java.util.Set;

public interface ModuleRepository extends JpaRepository<Module, Long> {

    default Set<Module> findAllSet() {
        return new HashSet<>(this.findAll());
    }
}
