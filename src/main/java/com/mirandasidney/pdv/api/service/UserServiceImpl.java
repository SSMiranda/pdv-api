package com.mirandasidney.pdv.api.service;

import com.mirandasidney.pdv.api.domain.User;
import com.mirandasidney.pdv.api.dto.request.UserPostRequestBody;
import com.mirandasidney.pdv.api.dto.response.UserResponse;
import com.mirandasidney.pdv.api.mapper.UserMapper;
import com.mirandasidney.pdv.api.repository.UserRepository;
import com.mirandasidney.pdv.api.service.interfaces.UserService;
import com.mirandasidney.pdv.api.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private static final String PROFILE = "user";
    private static UserMapper mapper = UserMapper.INSTANCE;

    private UserRepository userRepository;


    @Override
    public UserResponse save(UserPostRequestBody userPostRequestBody) {
        User user = mapper.toUser(userPostRequestBody);
        user.setProfile(PROFILE);
        user.setCreatedAt(Util.formatDate());
        User savedUser = userRepository.save(user);
        return mapper.toUserResponse(savedUser);
    }

    @Override
    public ResponseEntity<UserResponse> findUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.isPresent() ? ResponseEntity.ok(mapper.toUserResponse(user.get())) : ResponseEntity.notFound().build();
    }

    @Override
    public void removeUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) userRepository.delete(user.get());
    }


}
