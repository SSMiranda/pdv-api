package com.mirandasidney.pdv.api.controller.payload.request.functionality;

import com.mirandasidney.pdv.api.controller.payload.request.module.ModuleRequestById;
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
    @NotNull
    private ModuleRequestById module;

    private boolean isVisible;
}
