package com.mirandasidney.pdv.api.service.interfaces;

import com.mirandasidney.pdv.api.controller.dto.request.profile.ProfileRequest;
import com.mirandasidney.pdv.api.controller.dto.response.profile.ProfileResponse;
import com.mirandasidney.pdv.api.controller.dto.response.profile.ProfileResponseWithModules;
import org.springframework.http.ResponseEntity;

import java.util.Set;
import java.util.UUID;

public interface IProfileService {

    ResponseEntity<ProfileResponse> save(ProfileRequest profile);

    ResponseEntity<Set<ProfileResponse>> findAll();

    ResponseEntity<ProfileResponse> findProfileById(UUID id);

    ResponseEntity<?> removeProfile(UUID id);

    ResponseEntity<ProfileResponseWithModules> update(ProfileRequest profile, UUID id);
}
