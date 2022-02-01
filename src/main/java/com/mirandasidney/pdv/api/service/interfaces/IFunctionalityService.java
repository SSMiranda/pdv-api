package com.mirandasidney.pdv.api.service.interfaces;

import com.mirandasidney.pdv.api.controller.dto.request.functionality.FunctionalityRequest;
import com.mirandasidney.pdv.api.controller.dto.request.module.ModuleRequest;
import com.mirandasidney.pdv.api.controller.dto.response.FunctionalityResponse;
import com.mirandasidney.pdv.api.controller.dto.response.module.ModuleResponse;

public interface IFunctionalityService {

    FunctionalityResponse save(FunctionalityRequest module);
}
