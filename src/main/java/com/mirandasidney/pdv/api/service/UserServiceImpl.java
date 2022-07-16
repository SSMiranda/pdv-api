package com.mirandasidney.pdv.api.service;

import com.mirandasidney.pdv.api.controller.dto.request.user.UpdateUserRequest;
import com.mirandasidney.pdv.api.controller.dto.request.user.SignupRequest;
import com.mirandasidney.pdv.api.controller.dto.response.user.UserResponse;
import com.mirandasidney.pdv.api.entities.Role;
import com.mirandasidney.pdv.api.entities.User;
import com.mirandasidney.pdv.api.exception.ResourceNotFoundException;
import com.mirandasidney.pdv.api.exception.ValidationException;
import com.mirandasidney.pdv.api.mapper.UserMapper;
import com.mirandasidney.pdv.api.repository.AuthorityRepository;
import com.mirandasidney.pdv.api.repository.UserRepository;
import com.mirandasidney.pdv.api.security.services.UserDetailsImpl;
import com.mirandasidney.pdv.api.service.interfaces.IUserService;
import com.mirandasidney.pdv.api.utils.DateUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
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

        final Set<Role> roles = user.getRoles().stream()
                .map(role -> authorityRepository.findById(role.getUuid())
                        .orElseThrow(() -> new ResourceNotFoundException("Profile not found with UUID: " + role.getUuid())))
                .collect(Collectors.toSet());

        final URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/api/v1/{username}")
                .buildAndExpand(user.getUsername())
                .toUri();

            User newUser = mapper.toUser(user);
            newUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            newUser.setRoles(roles);
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

    @Override
    public ResponseEntity<UserResponse> userPartlyUpdate(UpdateUserRequest userUpdate, UUID id) {
        return repository.findById(id)
                .map(user -> {
                    if(userUpdate.getRole() != null) {
                        Role p = authorityRepository
                                .findById(userUpdate.getRole().getUuid())
                                .orElseThrow(() -> new ResourceNotFoundException("Profile not found with UUID" + userUpdate.getRole().getUuid()));
//                        user.addRole(p);
                    }
                    if(userUpdate.getUsername() != null) user.setUsername(userUpdate.getUsername());
                    if(userUpdate.getFirstname() != null) user.setFirstname(userUpdate.getFirstname());
                    if(userUpdate.getLastname() != null) user.setLastname(userUpdate.getLastname());
                    if(userUpdate.getPhone() != null) user.setPhone(userUpdate.getPhone());
                    if(userUpdate.getActive() != null) user.setActive((userUpdate.getActive()));
                    if(userUpdate != null) user.setUpdated(DateUtils.getDateTime());

            return ResponseEntity.ok().body(mapper.toUserResponse(repository.save(user)));
        }).orElseThrow(() -> new ResourceNotFoundException("User not found with UUID " + id));
    }
}
