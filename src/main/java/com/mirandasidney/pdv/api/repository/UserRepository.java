package com.mirandasidney.pdv.api.repository;

import com.mirandasidney.pdv.api.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, UUID> {

    @Query(value = "select u from User u where u.username = ?1")
    User findUserByUsername(String username);

    @Override
    @NonNull
    default Set<User> findAll() {
        return new HashSet<>(this.findAll());
    }
}
