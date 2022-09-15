package com.mirandasidney.pdv.api.service.interfaces;

import com.mirandasidney.pdv.api.controller.payload.request.module.ModuleRequest;
import com.mirandasidney.pdv.api.controller.payload.response.module.ModuleResponse;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface IModuleService {

    ResponseEntity<ModuleResponse> save(ModuleRequest module);

    ResponseEntity<Set<ModuleResponse>> listAllModules();
}
