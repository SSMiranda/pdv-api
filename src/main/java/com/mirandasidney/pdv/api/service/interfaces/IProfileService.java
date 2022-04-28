package com.mirandasidney.pdv.api.service.interfaces;

import com.mirandasidney.pdv.api.controller.dto.request.profile.ProfileRequest;
import com.mirandasidney.pdv.api.controller.dto.response.profile.ProfileResponse;
import com.mirandasidney.pdv.api.controller.dto.response.profile.ProfileResponseAllAttribute;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

public interface IProfileService {

    @Transactional
    ResponseEntity<ProfileResponse> save(ProfileRequest profile);

    ResponseEntity<Set<ProfileResponseAllAttribute>> findAll();

    ResponseEntity<ProfileResponseAllAttribute> findProfileById(UUID id);

    @Transactional
    ResponseEntity<Object> removeProfile(UUID id);

    @Transactional
    ResponseEntity<ProfileResponseAllAttribute> update(ProfileRequest profile, UUID id);
}
