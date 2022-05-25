package com.mirandasidney.pdv.api.repository;

import com.mirandasidney.pdv.api.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface ProfileRepository extends JpaRepository<Role, UUID> {

    @Query("select p from Role p join fetch p.modules")
//    @EntityGraph(attributePaths = "modules")
    default Set<Role> findAllSet() {
        return new HashSet<>(this.findAll());
    }

    Optional<Role> findByProfileName(String profileName);
}
