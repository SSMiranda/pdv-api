package com.mirandasidney.pdv.api.service.interfaces;

import com.mirandasidney.pdv.api.controller.dto.request.functionality.FunctionalityRequest;
import com.mirandasidney.pdv.api.controller.dto.response.functionality.FunctionalityResponse;

import java.util.Set;

public interface IFunctionalityService {

    FunctionalityResponse save(FunctionalityRequest module);

    Set<FunctionalityResponse> listAllFunctionality();
}
