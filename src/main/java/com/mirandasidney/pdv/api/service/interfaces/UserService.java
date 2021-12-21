package com.mirandasidney.pdv.api.service.interfaces;

import com.mirandasidney.pdv.api.controller.dto.request.UserPostRequestBody;
import com.mirandasidney.pdv.api.controller.dto.request.UserPutRequestByUser;
import com.mirandasidney.pdv.api.controller.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse save(UserPostRequestBody user);
    UserResponse findUserById(Long id);
    List<UserResponse> findAll();
    boolean removeUser(Long id);
    UserResponse update(UserPutRequestByUser user, Long id);
}
