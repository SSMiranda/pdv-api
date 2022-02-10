package com.mirandasidney.pdv.api.repository;

import com.mirandasidney.pdv.api.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public interface ProfileRepository extends JpaRepository<Profile, UUID> {
    default Set<Profile> findAllSet() {
        return this.findAll().stream().collect(Collectors.toSet());
    }
}
