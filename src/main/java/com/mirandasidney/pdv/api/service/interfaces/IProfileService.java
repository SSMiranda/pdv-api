package com.mirandasidney.pdv.api.service.interfaces;

import com.mirandasidney.pdv.api.controller.dto.request.profile.ProfileRequest;
import com.mirandasidney.pdv.api.controller.dto.response.profile.ProfileResponse;
import com.mirandasidney.pdv.api.controller.dto.response.profile.ProfileResponseWithModules;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

public interface IProfileService {

    @Transactional
    ResponseEntity<ProfileResponse> save(ProfileRequest profile);

    ResponseEntity<Set<ProfileResponse>> findAll();

    ResponseEntity<ProfileResponse> findProfileById(UUID id);

    @Transactional
    ResponseEntity<?> removeProfile(UUID id);

    @Transactional
    ResponseEntity<ProfileResponseWithModules> update(ProfileRequest profile, UUID id);
}
