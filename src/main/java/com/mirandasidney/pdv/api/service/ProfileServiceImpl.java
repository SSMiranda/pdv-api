package com.mirandasidney.pdv.api.service;

import com.mirandasidney.pdv.api.controller.dto.request.role.RoleRequest;
import com.mirandasidney.pdv.api.controller.dto.response.role.RoleResponse;
import com.mirandasidney.pdv.api.controller.dto.response.role.ProfileResponseAllAttribute;
import com.mirandasidney.pdv.api.domain.Role;
import com.mirandasidney.pdv.api.exception.ResourceNotFoundException;
import com.mirandasidney.pdv.api.exception.ValidationException;
import com.mirandasidney.pdv.api.mapper.ProfileMapper;
import com.mirandasidney.pdv.api.repository.AuthorityRepository;
import com.mirandasidney.pdv.api.service.interfaces.IProfileService;
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
public class ProfileServiceImpl implements IProfileService {

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
                .path("/api/v1/profiles/{profileName}")
                .buildAndExpand(newProfile.getName())
                .toUri();

        Role roleModel = mapper.toModel(newProfile);
        return ResponseEntity.created(uri).body(mapper.toDto(repository.save(roleModel)));
    }

    @Override
    public ResponseEntity<Set<ProfileResponseAllAttribute>> findAll() {
        Set<Role> role = repository.findAllSet();
        if(role.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok().body(mapper.toProfileListDto(role));
    }

    @Override
    public ResponseEntity<ProfileResponseAllAttribute> findProfileById(UUID id) {
        return repository.findById(id)
                .map(role -> ResponseEntity.ok().body(mapper.toDtoFull(role)))
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
    public ResponseEntity<ProfileResponseAllAttribute> update(RoleRequest roleRequest, UUID id) {
        final Optional<Role> role = repository.findById(id);
        return role
        .map(p -> {
                    if(roleRequest.getName() != null) p.setName(roleRequest.getName());
                    if(roleRequest.getDescription() != null) p.setDescription(roleRequest.getDescription());
                    repository.save(p);
                    return ResponseEntity.ok().body(mapper.toDtoFull(p));
                }).orElseThrow(() -> new ResourceNotFoundException("Profile not found with UUID: " + id));
    }

}
