package com.mirandasidney.pdv.api.controller.dto.request.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ModuleRequest {
    private boolean isChecked;
    @NotNull
    private String name;
    @NotNull
    private String description;
}
