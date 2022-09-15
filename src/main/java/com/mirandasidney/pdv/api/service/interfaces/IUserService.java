package com.mirandasidney.pdv.api.service.interfaces;

import com.mirandasidney.pdv.api.controller.payload.request.user.UpdateUserRequest;
import com.mirandasidney.pdv.api.controller.payload.request.user.SignupRequest;
import com.mirandasidney.pdv.api.controller.payload.response.user.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface IUserService {

    ResponseEntity<UserResponse> save(SignupRequest user);

    @Transactional(readOnly = true)
    ResponseEntity<UserResponse> findUserById(UUID id);

    @Transactional(readOnly = true)
    Page<UserResponse> findAll(int page, int size);

    ResponseEntity<Object> removeUser(UUID id);

    ResponseEntity<UserResponse> update(UpdateUserRequest user, UUID id);

//    ResponseEntity<UserResponse> userPartlyUpdate(UpdateUserRequest user, UUID id);

    ResponseEntity<Boolean> checkByUsername(String username);
}
