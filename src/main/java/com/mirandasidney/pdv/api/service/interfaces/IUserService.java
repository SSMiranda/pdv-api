package com.mirandasidney.pdv.api.service.interfaces;

import com.mirandasidney.pdv.api.controller.dto.request.user.UserPostRequestBody;
import com.mirandasidney.pdv.api.controller.dto.request.user.UserPutRequest;
import com.mirandasidney.pdv.api.controller.dto.response.user.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface IUserService {
    ResponseEntity<UserResponse> save(UserPostRequestBody user);

    ResponseEntity<UserResponse> findUserById(Long id);

    Page<UserResponse> findAll(int page, int size);

    ResponseEntity<Void> removeUser(Long id);

    ResponseEntity<UserResponse> update(UserPutRequest user, Long id);
}
