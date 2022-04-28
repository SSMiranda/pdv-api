package com.mirandasidney.pdv.api.controller.dto.response.profile;

import com.mirandasidney.pdv.api.controller.dto.response.functionality.FunctionalityResponse;
import com.mirandasidney.pdv.api.controller.dto.response.module.ModuleResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProfileResponseAllAttribute {
    private UUID uuid;
    private String profileName;
    private String description;
    private Set<ModuleResponse> modules;
    private Set<FunctionalityResponse> functionalities;
}
