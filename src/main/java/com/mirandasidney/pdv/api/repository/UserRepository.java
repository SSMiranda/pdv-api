package com.mirandasidney.pdv.api.repository;

import com.mirandasidney.pdv.api.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    default Set<User> findAllSet() {
        return new HashSet<>(this.findAll());
    }
}
