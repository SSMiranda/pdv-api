package com.mirandasidney.pdv.api.service;

import com.mirandasidney.pdv.api.controller.dto.request.role.RoleRequest;
import com.mirandasidney.pdv.api.controller.dto.response.role.RoleResponse;
import com.mirandasidney.pdv.api.entities.Role;
import com.mirandasidney.pdv.api.exception.ResourceNotFoundException;
import com.mirandasidney.pdv.api.exception.ValidationException;
import com.mirandasidney.pdv.api.mapper.ProfileMapper;
import com.mirandasidney.pdv.api.repository.AuthorityRepository;
import com.mirandasidney.pdv.api.service.interfaces.IRoleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RoleServiceImpl implements IRoleService {

    private static final ProfileMapper mapper = ProfileMapper.INSTANCE;
    private final AuthorityRepository repository;

    @Override
    public ResponseEntity<RoleResponse> save(RoleRequest newProfile) {
        Optional<Role> role = repository.findByName(newProfile.getName());
        if(role.isPresent()) {
            throw new ValidationException("Profile already exist with name: " + newProfile.getName());
        }

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/api/v1/profiles/{role}")
                .buildAndExpand(newProfile.getName())
                .toUri();

        Role roleModel = mapper.toModel(newProfile);
        return ResponseEntity.created(uri).body(mapper.toDto(repository.save(roleModel)));
    }

    @Override
    public ResponseEntity<Set<RoleResponse>> findAll() {
        Set<Role> roles = repository.findAllSet();
        if(roles.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok().body(mapper.toProfileListDto(roles));
    }

    @Override
    public ResponseEntity<RoleResponse> findProfileById(UUID id) {
        return repository.findById(id)
                .map(role -> ResponseEntity.ok().body(mapper.toDto(role)))
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found with UUID: " + id));
    }

    @Override
    public ResponseEntity<Object> removeProfile(UUID id) {
        return repository.findById(id).map(role -> {
            repository.delete(role);
            return ResponseEntity.noContent().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Category not found with UUID: " + id));
    }

    @Override
    public ResponseEntity<RoleResponse> update(RoleRequest roleRequest, UUID id) {
        final Optional<Role> role = repository.findById(id);
        return role.map(p -> {
                    if(roleRequest.getName() != null) p.setName(roleRequest.getName());
                    if(roleRequest.getDescription() != null) p.setDescription(roleRequest.getDescription());
                    repository.save(p);
                    return ResponseEntity.ok().body(mapper.toDto(p));
                }).orElseThrow(() -> new ResourceNotFoundException("Profile not found with UUID: " + id));
    }

}
