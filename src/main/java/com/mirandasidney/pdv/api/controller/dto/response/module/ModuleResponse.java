package com.mirandasidney.pdv.api.controller.dto.response.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ModuleResponse {

    private UUID uuid;
    private boolean enable;
    private String name;
    private String description;
//    private Set<FunctionalityResponse> functionalities;
}
