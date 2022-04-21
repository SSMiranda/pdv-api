package com.mirandasidney.pdv.api.repository;

import com.mirandasidney.pdv.api.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface ProfileRepository extends JpaRepository<Profile, UUID> {

    @Query("select p from Profile p join fetch p.modules")
//    @EntityGraph(attributePaths = "modules")
    default Set<Profile> findAllSet() {
        return new HashSet<>(this.findAll());
    }

    Optional<Profile> findByProfileName(String profileName);
}
