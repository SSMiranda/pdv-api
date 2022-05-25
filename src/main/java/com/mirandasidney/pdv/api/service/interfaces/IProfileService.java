package com.mirandasidney.pdv.api.service.interfaces;

import com.mirandasidney.pdv.api.controller.dto.request.role.ProfileRequest;
import com.mirandasidney.pdv.api.controller.dto.response.role.ProfileResponse;
import com.mirandasidney.pdv.api.controller.dto.response.role.ProfileResponseAllAttribute;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

public interface IProfileService {

    @Transactional
    ResponseEntity<ProfileResponse> save(ProfileRequest role);

    ResponseEntity<Set<ProfileResponseAllAttribute>> findAll();

    ResponseEntity<ProfileResponseAllAttribute> findProfileById(UUID id);

    @Transactional
    ResponseEntity<Object> removeProfile(UUID id);

    @Transactional
    ResponseEntity<ProfileResponseAllAttribute> update(ProfileRequest role, UUID id);
}
