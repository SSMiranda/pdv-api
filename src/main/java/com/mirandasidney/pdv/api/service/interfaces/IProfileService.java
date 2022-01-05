package com.mirandasidney.pdv.api.service.interfaces;

import com.mirandasidney.pdv.api.controller.dto.request.profile.ProfilePostRequest;
import com.mirandasidney.pdv.api.controller.dto.response.profile.ProfileResponse;
import org.springframework.http.ResponseEntity;

public interface IProfileService {

    ResponseEntity<ProfileResponse> save(ProfilePostRequest profile);

//    ResponseEntity<Set<CategoryWithListProductResponse>> findAll();
//
//    ResponseEntity<CategoryWithListProductResponse> findProfileById(Long id);
//
//    ResponseEntity<Void> removeCategory(Long id);
//
//    ResponseEntity<ProfileResponse> update(ProfilePostRequest category, Long id);
}
