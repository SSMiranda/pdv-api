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
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

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
    public UserResponse findUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuário não localizado com o ID: !" + id));
        return mapper.toUserResponse(user);
    }


}
