package com.mirandasidney.pdv.api.controller.dto.response.module;

import com.mirandasidney.pdv.api.controller.dto.response.functionality.FunctionalityResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ModuleResponse {

    private UUID uuid;
    private boolean enable;
    private String name;
    private String description;
    private Set<FunctionalityResponse> functionalities;


}
