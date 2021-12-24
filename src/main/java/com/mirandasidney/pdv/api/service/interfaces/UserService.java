package com.mirandasidney.pdv.api.service.interfaces;

import com.mirandasidney.pdv.api.controller.dto.request.user.UserPostRequestBody;
import com.mirandasidney.pdv.api.controller.dto.request.user.UserPutRequestByUser;
import com.mirandasidney.pdv.api.controller.dto.response.user.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse save(UserPostRequestBody user);

    UserResponse findUserById(Long id);

    List<UserResponse> findAll();

    boolean removeUser(Long id);

    UserResponse update(UserPutRequestByUser user, Long id);
}
