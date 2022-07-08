package com.mirandasidney.pdv.api.service.interfaces;

import com.mirandasidney.pdv.api.controller.dto.request.user.UpdateUserRequest;
import com.mirandasidney.pdv.api.controller.dto.request.user.UserPostRequestBody;
import com.mirandasidney.pdv.api.controller.dto.response.user.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface IUserService extends UserDetailsService {

    ResponseEntity<UserResponse> save(UserPostRequestBody user);

    @Transactional(readOnly = true)
    ResponseEntity<UserResponse> findUserById(UUID id);

    @Transactional(readOnly = true)
    Page<UserResponse> findAll(int page, int size);

    ResponseEntity<Object> removeUser(UUID id);

    ResponseEntity<UserResponse> update(UpdateUserRequest user, UUID id);

    ResponseEntity<UserResponse> userPartlyUpdate(UpdateUserRequest user, UUID id);

    ResponseEntity<Boolean> getByUsername(String username);
}
