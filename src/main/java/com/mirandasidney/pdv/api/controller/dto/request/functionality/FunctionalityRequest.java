package com.mirandasidney.pdv.api.controller.dto.request.functionality;

import com.mirandasidney.pdv.api.controller.dto.request.module.ModuleRequestById;

public class FunctionalityRequest {

    private String name;
    private String description;
    private boolean canView;
    private boolean canEdit;
    private ModuleRequestById module;
}
