package com.mirandasidney.pdv.api.service.interfaces;

import com.mirandasidney.pdv.api.controller.dto.request.functionality.FunctionalityRequest;
import com.mirandasidney.pdv.api.controller.dto.response.functionality.FunctionalityResponse;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.Set;

public interface IFunctionalityService {

    ResponseEntity<FunctionalityResponse> save(FunctionalityRequest functionality);

    ResponseEntity<Set<FunctionalityResponse>> listAllFunctionality();

    ResponseEntity<FunctionalityResponse> findFunctionalityById(Long id);

}
