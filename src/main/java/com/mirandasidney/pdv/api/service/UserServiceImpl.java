package com.mirandasidney.pdv.api.service;

import com.mirandasidney.pdv.api.controller.payload.request.user.SignupRequest;
import com.mirandasidney.pdv.api.controller.payload.request.user.UpdateUserRequest;
import com.mirandasidney.pdv.api.controller.payload.response.user.UserResponse;
import com.mirandasidney.pdv.api.entities.Role;
import com.mirandasidney.pdv.api.entities.User;
import com.mirandasidney.pdv.api.exception.ResourceNotFoundException;
import com.mirandasidney.pdv.api.exception.ValidationException;
import com.mirandasidney.pdv.api.mapper.UserMapper;
import com.mirandasidney.pdv.api.repository.AuthorityRepository;
import com.mirandasidney.pdv.api.repository.UserRepository;
import com.mirandasidney.pdv.api.service.interfaces.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements IUserService {
    private static final UserMapper mapper = UserMapper.INSTANCE;
    private UserRepository repository;
    private AuthorityRepository authorityRepository;

    @Override
    @Transactional
    public ResponseEntity<Boolean> checkByUsername(String username) {
        return ResponseEntity.ok(repository.existsByUsername(username));
    }

    @Override
    @Transactional
    public ResponseEntity<UserResponse> save(SignupRequest user) {
        final boolean existsByUsername = repository.existsByUsername(user.getUsername());

        if(existsByUsername) {
            throw new ValidationException("Username already exists, try again!");
        }

        final URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/api/v1/{username}")
                .buildAndExpand(user.getUsername())
                .toUri();

            PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            User newUser = User.builder()
                    .firstname(user.getFirstname())
                    .lastname(user.getLastname())
                    .username(user.getUsername())
                    .phone(user.getPhone())
                    .password(encoder.encode(user.getPassword()))
                    .roles(mapAuthority(user))
                    .build();
            User savedUser = repository.save(newUser);

            return ResponseEntity.created(uri).body(mapper.toUserResponse(savedUser));
    }

    @Override
    public ResponseEntity<UserResponse> findUserById(UUID id) {
        return repository.findById(id)
                .map(user -> ResponseEntity.ok().body(mapper.toUserResponse(user)))
                .orElseThrow(() -> new ResourceNotFoundException("User not found with UUID: " + id));
    }

    @Override
    public Page<UserResponse> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<User> users = this.repository.findAll(pageRequest);

        return users.map(UserResponse::new);
    }

    @Override
    public ResponseEntity<Object> removeUser(UUID id) {
        return repository.findById(id)
                .map(user -> {
                    repository.delete(user);
                    return ResponseEntity.noContent().build();
                }).orElseThrow(() -> new ResourceNotFoundException("User not found with UUID: " + id));
    }


    @Override
    public ResponseEntity<UserResponse> update(UpdateUserRequest userUpdate, UUID id) {
        return repository.findById(id)
                .map(user -> {
                    BeanUtils.copyProperties(userUpdate, user);
                    repository.save(user);
                    return ResponseEntity.ok().body(mapper.toUserResponse(user));
                }).orElseThrow(() -> new ResourceNotFoundException("User not found with UUID: " + id));
    }

    private Set<Role> mapAuthority(SignupRequest user) {
        return user.getRoles().stream()
                .map(role -> authorityRepository.findById(role.getUuid())
                        .orElseThrow(() -> new ResourceNotFoundException("Profile not found with UUID: " + role.getUuid())))
                .collect(Collectors.toSet());
    }
}
