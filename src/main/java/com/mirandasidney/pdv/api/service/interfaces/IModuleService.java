package com.mirandasidney.pdv.api.service.interfaces;

import com.mirandasidney.pdv.api.controller.dto.request.module.ModuleRequest;
import com.mirandasidney.pdv.api.controller.dto.response.module.ModuleResponse;

import java.util.Set;

public interface IModuleService {

    ModuleResponse save(ModuleRequest module);
    Set<ModuleResponse> listAllModules();
}