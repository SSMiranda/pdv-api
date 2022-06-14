package com.mirandasidney.pdv.api.repository;

import com.mirandasidney.pdv.api.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface AuthorityRepository extends JpaRepository<Role, UUID> {

    default Set<Role> findAllSet() {
        return new HashSet<>(this.findAll());
    }

    Optional<Role> findByName(String profileName);
}
