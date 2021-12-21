package com.mirandasidney.pdv.api.service;

import com.mirandasidney.pdv.api.controller.dto.request.UserPostRequestBody;
import com.mirandasidney.pdv.api.controller.dto.request.UserPutRequestByUser;
import com.mirandasidney.pdv.api.controller.dto.response.UserResponse;
import com.mirandasidney.pdv.api.domain.User;
import com.mirandasidney.pdv.api.mapper.UserMapper;
import com.mirandasidney.pdv.api.repository.UserRepository;
import com.mirandasidney.pdv.api.service.interfaces.UserService;
import com.mirandasidney.pdv.api.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private static final String PROFILE = "user";
    private static UserMapper mapper = UserMapper.INSTANCE;

    private UserRepository userRepository;

    private User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    public UserResponse save(UserPostRequestBody userPostRequestBody) {
        User user = mapper.toUser(userPostRequestBody);
        user.setProfile(PROFILE);
        user.setCreatedAt(Util.formatDate());
        User savedUser = userRepository.save(user);
        return mapper.toUserResponse(savedUser);
        }

        @Override
    public UserResponse findUserById(Long id) {
        User user = findById(id);
        return mapper.toUserResponse(user);

    }

    @Override
    public boolean removeUser(Long id) {
        User user = findById(id);
        if (user != null) {
            userRepository.delete(user);
            return true;
        }
           return false;
    }

    @Override
    public UserResponse update(UserPutRequestByUser userUpdate, Long id) {
        User user = findById(id);
        BeanUtils.copyProperties(userUpdate, user);
        User save = userRepository.save(user);
        return mapper.toUserResponse(save);
    }

}
