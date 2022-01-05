package com.mirandasidney.pdv.api.repository;

import com.mirandasidney.pdv.api.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
