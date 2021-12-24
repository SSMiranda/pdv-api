package com.mirandasidney.pdv.api.service.interfaces;

import com.mirandasidney.pdv.api.controller.dto.request.user.UserPostRequestBody;
import com.mirandasidney.pdv.api.controller.dto.request.user.UserPutRequestByUser;
import com.mirandasidney.pdv.api.controller.dto.response.user.UserResponse;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface IUserService {
    ResponseEntity<UserResponse> save(UserPostRequestBody user);

    ResponseEntity<UserResponse> findUserById(Long id);

    ResponseEntity<Set<UserResponse>> findAll();

    ResponseEntity<Void> removeUser(Long id);

    ResponseEntity<UserResponse> update(UserPutRequestByUser user, Long id);
}
