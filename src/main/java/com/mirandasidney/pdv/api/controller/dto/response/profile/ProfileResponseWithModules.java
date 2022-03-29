package com.mirandasidney.pdv.api.controller.dto.response.profile;

import com.mirandasidney.pdv.api.controller.dto.response.module.ModuleResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProfileResponseWithModules {

    private UUID uuid;
    private String profileName;
    private String description;
    private Set<ModuleResponse> modules;
}
