package com.mirandasidney.pdv.api.service.interfaces;

import com.mirandasidney.pdv.api.controller.dto.request.UserPostRequestBody;
import com.mirandasidney.pdv.api.controller.dto.request.UserPutRequestByUser;
import com.mirandasidney.pdv.api.controller.dto.response.UserResponse;

public interface UserService {
    UserResponse save(UserPostRequestBody user);
    UserResponse findUserById(Long id);
    boolean removeUser(Long id);
    UserResponse update(UserPutRequestByUser user, Long id);
//    UserResponse update(UserPutRequestByAdmin user, Long id);
}
