package com.mirandasidney.pdv.api.service;

import com.mirandasidney.pdv.api.controller.dto.request.profile.ProfilePostRequest;
import com.mirandasidney.pdv.api.controller.dto.response.profile.ProfileResponse;
import com.mirandasidney.pdv.api.domain.Profile;
import com.mirandasidney.pdv.api.mapper.ProfileMapper;
import com.mirandasidney.pdv.api.repository.ProfileRepository;
import com.mirandasidney.pdv.api.service.interfaces.IProfileService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProfileServiceImpl implements IProfileService {

    private static final ProfileMapper mapper = ProfileMapper.INSTANCE;
    private final ProfileRepository repository;

    @Override
    public ResponseEntity<ProfileResponse> save(ProfilePostRequest newProfile) {
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/api/v1/profiles/{id}")
                .buildAndExpand(newProfile)
                .toUri();

        Profile profile = mapper.toModel(newProfile);

        return ResponseEntity.created(uri).body(mapper.toDto(repository.save(profile)));
    }

}
