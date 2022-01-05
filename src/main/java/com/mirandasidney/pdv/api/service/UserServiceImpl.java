package com.mirandasidney.pdv.api.service;

import com.mirandasidney.pdv.api.controller.dto.request.user.UserPostRequestBody;
import com.mirandasidney.pdv.api.controller.dto.request.user.UserPutRequest;
import com.mirandasidney.pdv.api.controller.dto.response.user.UserResponse;
import com.mirandasidney.pdv.api.domain.Profile;
import com.mirandasidney.pdv.api.domain.User;
import com.mirandasidney.pdv.api.mapper.ProfileMapper;
import com.mirandasidney.pdv.api.mapper.UserMapper;
import com.mirandasidney.pdv.api.repository.ProfileRepository;
import com.mirandasidney.pdv.api.repository.UserRepository;
import com.mirandasidney.pdv.api.service.interfaces.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements IUserService {

    private static final UserMapper mapper = UserMapper.INSTANCE;
    private static final ProfileMapper profileMapper = ProfileMapper.INSTANCE;

    private UserRepository repository;
    private ProfileRepository profileRepository;

    @Override
    public ResponseEntity<UserResponse> save(UserPostRequestBody user) {
        final URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/api/v1/{username}")
                .buildAndExpand(user.getUsername())
                .toUri();
        Optional<Profile> profile = profileRepository.findById(user.getProfile().getId());
        if(profile.isPresent()){
            User newUser = mapper.toUser(user);
            newUser.setProfile(profile.get());
            User savedUser = repository.save(newUser);
            return ResponseEntity.created(uri).body(mapper.toUserResponse(repository.save(savedUser)));
        }

        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<UserResponse> findUserById(Long id) {
        return repository.findById(id)
                .map(user -> ResponseEntity.ok().body(mapper.toUserResponse(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Set<UserResponse>> findAll() {
        return ResponseEntity.ok().body(mapper.toUserResponse(repository.findAllSet()));
    }

    @Override
    public ResponseEntity<Void> removeUser(Long id) {
        Optional<User> user = repository.findById(id);
        if (user.isPresent()) {
            repository.delete(user.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<UserResponse> update(UserPutRequest userUpdate, Long id) {
        return repository.findById(id)
                .map(user -> {
                    BeanUtils.copyProperties(userUpdate, user);
                    repository.save(user);
                    return ResponseEntity.ok().body(mapper.toUserResponse(user));
                }).orElse(ResponseEntity.badRequest().build());
    }

}
