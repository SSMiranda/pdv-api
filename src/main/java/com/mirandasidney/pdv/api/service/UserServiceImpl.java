package com.mirandasidney.pdv.api.service;

import com.mirandasidney.pdv.api.controller.dto.request.user.UserPostRequestBody;
import com.mirandasidney.pdv.api.controller.dto.request.user.UpdateUserRequest;
import com.mirandasidney.pdv.api.controller.dto.response.user.UserResponse;
import com.mirandasidney.pdv.api.domain.Profile;
import com.mirandasidney.pdv.api.domain.User;
import com.mirandasidney.pdv.api.exception.ResourceNotFoundException;
import com.mirandasidney.pdv.api.mapper.UserMapper;
import com.mirandasidney.pdv.api.repository.ProfileRepository;
import com.mirandasidney.pdv.api.repository.UserRepository;
import com.mirandasidney.pdv.api.service.interfaces.IUserService;
import com.mirandasidney.pdv.api.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements IUserService {

    private static final UserMapper mapper = UserMapper.INSTANCE;

    private UserRepository repository;
    private ProfileRepository profileRepository;

    @Override
    public ResponseEntity<UserResponse> save(UserPostRequestBody user) {
        Profile profile = profileRepository
                .findById(user.getProfile().getUuid())
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found with UUID: " + user.getProfile().getUuid()));

        final URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/api/v1/{username}")
                .buildAndExpand(user.getUsername())
                .toUri();

            User newUser = mapper.toUser(user);
            newUser.setProfile(profile);
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
        Page<User> results = this.repository.findAll(pageRequest);
        return results.map(UserResponse::new);
    }

    @Override
    public ResponseEntity<?> removeUser(UUID id) {
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
                    if(userUpdate.getProfile() != null) {
                        Profile p = profileRepository
                                .findById(userUpdate.getProfile().getUuid())
                                .orElseThrow(() -> new ResourceNotFoundException("Profile not found with UUID" + userUpdate.getProfile().getUuid()));
                        user.setProfile(p);
                    }
                    if(userUpdate.getUsername() != null) user.setUsername(userUpdate.getUsername());
                    if(userUpdate.getFirstname() != null) user.setFirstname(userUpdate.getFirstname());
                    if(userUpdate.getLastname() != null) user.setLastname(userUpdate.getLastname());
                    if(userUpdate.getPhone() != null) user.setPhone(userUpdate.getPhone());
                    if(userUpdate.getActive() != null) user.setActive((userUpdate.getActive()));
                    if(userUpdate != null) user.setUpdated(Util.formatDate());

            return ResponseEntity.ok().body(mapper.toUserResponse(repository.save(user)));
        }).orElseThrow(() -> new ResourceNotFoundException("User not found with UUID " + id));
    }
}
