package com.mirandasidney.pdv.api.service;

import com.mirandasidney.pdv.api.controller.dto.request.profile.ProfileRequest;
import com.mirandasidney.pdv.api.controller.dto.response.profile.ProfileResponse;
import com.mirandasidney.pdv.api.controller.dto.response.profile.ProfileResponseWithModules;
import com.mirandasidney.pdv.api.domain.Profile;
import com.mirandasidney.pdv.api.mapper.ProfileMapper;
import com.mirandasidney.pdv.api.repository.ProfileRepository;
import com.mirandasidney.pdv.api.service.interfaces.IProfileService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.lang.reflect.Field;
import java.net.URI;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProfileServiceImpl implements IProfileService {

    private static final ProfileMapper mapper = ProfileMapper.INSTANCE;
    private final ProfileRepository repository;

    @Override
    public ResponseEntity<ProfileResponse> save(ProfileRequest newProfile) {
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/api/v1/profiles/{id}")
                .buildAndExpand(newProfile)
                .toUri();

        Profile profile = mapper.toModel(newProfile);
        return ResponseEntity.created(uri).body(mapper.toDto(repository.save(profile)));
    }

    @Override
    public ResponseEntity<Set<ProfileResponse>> findAll() {
        return ResponseEntity.ok().body(mapper.toProfileListDto(repository.findAll()));
    }

    @Override
    public ResponseEntity<ProfileResponse> findProfileById(UUID id) {
        return repository.findById(id)
                .map(profile -> ResponseEntity.ok().body(mapper.toDto(profile)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Void> removeProfile(UUID id) {
        Optional<Profile> profile = repository.findById(id);
        if (profile.isPresent() && profile.get().getUsers().isEmpty()) {
            repository.delete(profile.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<ProfileResponseWithModules> update(Map<Object, Object> fields, UUID id) {
        Optional<Profile> profile = repository.findById(id);
        if (profile.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Profile.class, (String) key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, profile.get(), value);
            });
            repository.saveAndFlush(profile.get());
            ResponseEntity.ok().body(mapper.toDtoFull(profile.get()));
        }
        return ResponseEntity.notFound().build();
    }

}
