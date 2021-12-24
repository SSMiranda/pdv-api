package com.mirandasidney.pdv.api.repository;

import com.mirandasidney.pdv.api.controller.dto.response.user.UserResponse;
import com.mirandasidney.pdv.api.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.stream.Collectors;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    default Set<User> findAllSet() {
        return this.findAll().stream().collect(Collectors.toSet());
    }
}
