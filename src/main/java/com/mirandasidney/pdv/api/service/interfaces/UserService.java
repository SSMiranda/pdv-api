package com.mirandasidney.pdv.api.service.interfaces;

import com.mirandasidney.pdv.api.dto.request.UserPostRequestBody;
import com.mirandasidney.pdv.api.dto.response.UserResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {
    UserResponse save(UserPostRequestBody user);
    ResponseEntity<UserResponse> findUserById(Long id);
    void removeUser(Long id);
}
