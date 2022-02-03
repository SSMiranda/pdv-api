package com.mirandasidney.pdv.api.controller.dto.request.functionality;

import com.mirandasidney.pdv.api.controller.dto.request.module.ModuleRequestById;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FunctionalityRequest {

    @NotNull
    private String name;
    @NotNull
    private String description;
    private boolean canView;
    private boolean canEdit;
    @NotNull
    private ModuleRequestById module;
}
