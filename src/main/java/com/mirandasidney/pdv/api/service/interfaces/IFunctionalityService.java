package com.mirandasidney.pdv.api.service.interfaces;

import com.mirandasidney.pdv.api.controller.payload.request.functionality.FunctionalityRequest;
import com.mirandasidney.pdv.api.controller.payload.response.functionality.FunctionalityResponse;
import org.springframework.http.ResponseEntity;

import java.util.Set;
import java.util.UUID;

public interface IFunctionalityService {

    ResponseEntity<FunctionalityResponse> save(FunctionalityRequest functionality);

    ResponseEntity<Set<FunctionalityResponse>> listAllFunctionality();

    ResponseEntity<FunctionalityResponse> findFunctionalityById(UUID id);

}
