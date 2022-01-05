package com.mirandasidney.pdv.api.service;

import com.mirandasidney.pdv.api.controller.dto.request.profile.ProfileRequest;
import com.mirandasidney.pdv.api.controller.dto.response.profile.ProfileResponse;
import com.mirandasidney.pdv.api.domain.Profile;
import com.mirandasidney.pdv.api.mapper.ProfileMapper;
import com.mirandasidney.pdv.api.repository.ProfileRepository;
import com.mirandasidney.pdv.api.service.interfaces.IProfileService;
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
        return ResponseEntity.ok().body(mapper.toProfileListDto(repository.findAllSet()));
    }

    @Override
    public ResponseEntity<ProfileResponse> findProfileById(Long id) {
        return repository.findById(id)
                .map(profile -> ResponseEntity.ok().body(mapper.toDto(profile)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Void> removeProfile(Long id) {
        Optional<Profile> profile = repository.findById(id);
        if (profile.isPresent() && profile.get().getUsers().isEmpty()) {
            repository.delete(profile.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<ProfileResponse> update(ProfileRequest categoryRequest, Long id) {
        return repository.findById(id)
                .map(category -> {
                    BeanUtils.copyProperties(categoryRequest, category);
                    repository.save(category);
                    return ResponseEntity.ok().body(mapper.toDto(category));
                }).orElse(ResponseEntity.badRequest().build());
    }

}
