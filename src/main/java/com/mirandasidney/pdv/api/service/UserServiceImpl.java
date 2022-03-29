package com.mirandasidney.pdv.api.service;

import com.mirandasidney.pdv.api.controller.dto.request.user.UserPostRequestBody;
import com.mirandasidney.pdv.api.controller.dto.request.user.UserPutRequest;
import com.mirandasidney.pdv.api.controller.dto.response.user.UserResponse;
import com.mirandasidney.pdv.api.domain.Profile;
import com.mirandasidney.pdv.api.domain.User;
import com.mirandasidney.pdv.api.mapper.UserMapper;
import com.mirandasidney.pdv.api.repository.ProfileRepository;
import com.mirandasidney.pdv.api.repository.UserRepository;
import com.mirandasidney.pdv.api.service.interfaces.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements IUserService {

    private static final UserMapper mapper = UserMapper.INSTANCE;

    private UserRepository repository;
    private ProfileRepository profileRepository;

    @Override
    public ResponseEntity<UserResponse> save(UserPostRequestBody user) {
        final URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/api/v1/{username}")
                .buildAndExpand(user.getUsername())
                .toUri();
        Optional<Profile> profile = profileRepository.findById(user.getProfile().getUuid());
        if (profile.isPresent()) {
            User newUser = mapper.toUser(user);
            newUser.setProfile(profile.get());
            User savedUser = repository.save(newUser);
            return ResponseEntity.created(uri).body(mapper.toUserResponse(savedUser));
        }

        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<UserResponse> findUserById(UUID id) {
        return repository.findById(id)
                .map(user -> ResponseEntity.ok().body(mapper.toUserResponse(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public Page<UserResponse> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<User> results = this.repository.findAll(pageRequest);
        return results.map(UserResponse::new);
    }

    @Override
    public ResponseEntity<Void> removeUser(UUID id) {
        Optional<User> user = repository.findById(id);
        if (user.isPresent()) {
            repository.delete(user.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<UserResponse> update(UserPutRequest userUpdate, UUID id) {
        return repository.findById(id)
                .map(user -> {
                    BeanUtils.copyProperties(userUpdate, user);
                    repository.save(user);
                    return ResponseEntity.ok().body(mapper.toUserResponse(user));
                }).orElse(ResponseEntity.badRequest().build());
    }

}
