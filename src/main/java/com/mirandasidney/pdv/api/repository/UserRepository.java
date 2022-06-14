package com.mirandasidney.pdv.api.repository;

import com.mirandasidney.pdv.api.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, UUID> {

    @Query(value = "select u from User u where u.username = ?1")
    User findUserByUsername(String username);

//    @NonNull
//    @Query("select new com.mirandasidney.pdv.api.controller.dto.response.user.UserResponse(u) from User u left join Role r")
//    default Set<UserResponse> findAllUsers() {
//        return new HashSet<>(this.findAll());
//    }
    @Override
    default Set<User> findAll() {
        return new HashSet<>(this.findAll());
    }

}
