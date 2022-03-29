package com.mirandasidney.pdv.api.repository;

import com.mirandasidney.pdv.api.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface ProfileRepository extends JpaRepository<Profile, UUID> {
    default Set<Profile> findAllSet() {
        return new HashSet<>(this.findAll());
    }

    Optional<Profile> findByProfileName(String profileName);
}
