package com.mirandasidney.pdv.api.service.interfaces;

import com.mirandasidney.pdv.api.dto.request.UserPostRequestBody;
import com.mirandasidney.pdv.api.dto.response.UserResponse;

public interface UserService {
    UserResponse save(UserPostRequestBody user);

    UserResponse findUserById(Long id);
}
