package com.mirandasidney.pdv.api.service;

import com.mirandasidney.pdv.api.controller.payload.request.role.RoleRequest;
import com.mirandasidney.pdv.api.controller.payload.response.role.RoleResponse;
import com.mirandasidney.pdv.api.entities.Role;
import com.mirandasidney.pdv.api.exception.ResourceNotFoundException;
import com.mirandasidney.pdv.api.exception.ValidationException;
import com.mirandasidney.pdv.api.mapper.ProfileMapper;
import com.mirandasidney.pdv.api.repository.AuthorityRepository;
import com.mirandasidney.pdv.api.service.interfaces.IRoleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RoleServiceImpl implements IRoleService {

    private static final ProfileMapper mapper = ProfileMapper.INSTANCE;
    private final AuthorityRepository repository;

    @Override
    public RoleResponse save(RoleRequest newProfile) {
        Optional<Role> role = repository.findByName(newProfile.getName());

        if(role.isPresent()) {
            throw new ValidationException("Profile already exist with name: " + newProfile.getName());
        }

        Role roleModel = mapper.toModel(newProfile);
        return mapper.toDto(repository.save(roleModel));
    }

    @Override
    public Set<RoleResponse> findAll() {
        return mapper.toProfileListDto(repository.findAllSet());
    }

    @Override
    public RoleResponse findProfileById(UUID id) {
        return repository.findById(id)
                .map(role -> mapper.toDto(role))
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found with UUID: " + id));
    }

    @Override
    public void removeProfile(UUID id) {
        Role role = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with UUID: " + id));
        repository.delete(role);
    }

    @Override
    public RoleResponse update(RoleRequest roleRequest, UUID id) {
        final Optional<Role> role = repository.findById(id);
        return role.map(p -> {
                    if(roleRequest.getName() != null) p.setName(roleRequest.getName());
                    if(roleRequest.getDescription() != null) p.setDescription(roleRequest.getDescription());
                    repository.save(p);
                    return mapper.toDto(p);
                }).orElseThrow(() -> new ResourceNotFoundException("Profile not found with UUID: " + id));
    }

}
