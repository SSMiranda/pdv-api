package com.mirandasidney.pdv.api.service;

import com.mirandasidney.pdv.api.controller.dto.request.user.UpdateUserRequest;
import com.mirandasidney.pdv.api.controller.dto.request.user.UserPostRequestBody;
import com.mirandasidney.pdv.api.controller.dto.response.user.UserResponse;
import com.mirandasidney.pdv.api.domain.Role;
import com.mirandasidney.pdv.api.domain.User;
import com.mirandasidney.pdv.api.exception.ResourceNotFoundException;
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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user =  Optional.ofNullable(repository.findUserByUsername(username))
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthorities());
    }

    @Override
    public ResponseEntity<UserResponse> save(UserPostRequestBody user) {
        Role role = profileRepository
                .findById(user.getRole().getUuid())
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found with UUID: " + user.getRole().getUuid()));

        final URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/api/v1/{username}")
                .buildAndExpand(user.getUsername())
                .toUri();

            User newUser = mapper.toUser(user);
//            newUser.setRole(role);
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
//        return repository.findById(id)
//                .map(user -> {
//                    if(userUpdate.getRole() != null) {
//                        Role p = profileRepository
//                                .findById(userUpdate.getRole().getUuid())
//                                .orElseThrow(() -> new ResourceNotFoundException("Profile not found with UUID" + userUpdate.getRole().getUuid()));
//                        user.setRole(p);
//                    }
//                    if(userUpdate.getUsername() != null) user.setUsername(userUpdate.getUsername());
//                    if(userUpdate.getFirstname() != null) user.setFirstname(userUpdate.getFirstname());
//                    if(userUpdate.getLastname() != null) user.setLastname(userUpdate.getLastname());
//                    if(userUpdate.getPhone() != null) user.setPhone(userUpdate.getPhone());
//                    if(userUpdate.getActive() != null) user.setActive((userUpdate.getActive()));
//                    if(userUpdate != null) user.setUpdated(Util.formatDate());
//
//            return ResponseEntity.ok().body(mapper.toUserResponse(repository.save(user)));
//        }).orElseThrow(() -> new ResourceNotFoundException("User not found with UUID " + id));
        return null;
    }
}
