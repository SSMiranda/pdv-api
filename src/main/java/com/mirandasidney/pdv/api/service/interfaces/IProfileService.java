package com.mirandasidney.pdv.api.service.interfaces;

import com.mirandasidney.pdv.api.controller.dto.request.profile.ProfileRequest;
import com.mirandasidney.pdv.api.controller.dto.response.profile.ProfileResponse;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface IProfileService {

    ResponseEntity<ProfileResponse> save(ProfileRequest profile);

    ResponseEntity<Set<ProfileResponse>> findAll();

    ResponseEntity<ProfileResponse> findProfileById(Long id);

    ResponseEntity<Void> removeProfile(Long id);

    ResponseEntity<ProfileResponse> update(ProfileRequest category, Long id);
}
