package com.mirandasidney.pdv.api.service.interfaces;

import com.mirandasidney.pdv.api.controller.dto.request.user.UserPostRequestBody;
import com.mirandasidney.pdv.api.controller.dto.request.user.UpdateUserRequest;
import com.mirandasidney.pdv.api.controller.dto.response.user.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface IUserService {

    @Transactional
    ResponseEntity<UserResponse> save(UserPostRequestBody user);

    ResponseEntity<UserResponse> findUserById(UUID id);

    Page<UserResponse> findAll(int page, int size);

    @Transactional
    ResponseEntity<?> removeUser(UUID id);

    @Transactional
    ResponseEntity<UserResponse> update(UpdateUserRequest user, UUID id);

    @Transactional
    ResponseEntity<UserResponse> userPartlyUpdate(UpdateUserRequest user, UUID id);
}
