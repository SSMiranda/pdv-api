package com.mirandasidney.pdv.api.service;

import com.mirandasidney.pdv.api.controller.dto.request.profile.ProfileRequest;
import com.mirandasidney.pdv.api.controller.dto.response.profile.ProfileResponse;
import com.mirandasidney.pdv.api.controller.dto.response.profile.ProfileResponseWithModules;
import com.mirandasidney.pdv.api.domain.Module;
import com.mirandasidney.pdv.api.domain.Profile;
import com.mirandasidney.pdv.api.exception.ResourceNotFoundException;
import com.mirandasidney.pdv.api.exception.ValidationException;
import com.mirandasidney.pdv.api.mapper.ProfileMapper;
import com.mirandasidney.pdv.api.repository.ModuleRepository;
import com.mirandasidney.pdv.api.repository.ProfileRepository;
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
    private final ProfileRepository repository;
    private final ModuleRepository moduleRepository;

    @Override
    public ResponseEntity<ProfileResponse> save(ProfileRequest newProfile) {
        Optional<Profile> profile = repository.findByProfileName(newProfile.getProfileName());
        if(profile.isPresent()) {
            throw new ValidationException("Profile already exist with name: " + newProfile.getProfileName());
        }

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/api/v1/profiles/{profileName}")
                .buildAndExpand(newProfile.getProfileName())
                .toUri();

        Profile profileModel = mapper.toModel(newProfile);
        return ResponseEntity.created(uri).body(mapper.toDto(repository.save(profileModel)));
    }

    @Override
    public ResponseEntity<Set<ProfileResponseWithModules>> findAll() {
        Set<Profile> profile = repository.findAllSet();
        if(profile.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok().body(mapper.toProfileListDto(profile));
    }

    @Override
    public ResponseEntity<ProfileResponseWithModules> findProfileById(UUID id) {
        return repository.findById(id)
                .map(profile -> ResponseEntity.ok().body(mapper.toDtoFull(profile)))
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found with UUID: " + id));
    }

    @Override
    public ResponseEntity<Object> removeProfile(UUID id) {
        return repository.findById(id).map(profile -> {
            repository.delete(profile);
            return ResponseEntity.noContent().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Category not found with UUID: " + id));
    }

    @Override
    public ResponseEntity<ProfileResponseWithModules> update(ProfileRequest profileUpdate, UUID id) {
        final Optional<Profile> profile = repository.findById(id);
        return profile
        .map(p -> {
                    if(profileUpdate.getProfileName() != null) p.setProfileName(profileUpdate.getProfileName());
                    if(profileUpdate.getDescription() != null) p.setDescription(profileUpdate.getDescription());
                    if(profileUpdate.getModule() != null) {
                        final Optional<Module> module = moduleRepository.findById(profileUpdate.getModule().getUuid());
                        if (module.isPresent()) {
                            p.appendModule(module.get());
                        }
                    }
                    repository.save(p);
                    return ResponseEntity.ok().body(mapper.toDtoFull(p));
                }).orElseThrow(() -> new ResourceNotFoundException("Profile not found with UUID: " + id));
    }

}
