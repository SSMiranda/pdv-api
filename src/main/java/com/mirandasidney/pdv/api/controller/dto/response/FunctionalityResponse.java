package com.mirandasidney.pdv.api.controller.dto.response;

import com.mirandasidney.pdv.api.controller.dto.response.module.ModuleResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FunctionalityResponse {

    private Long id;
    private String name;
    private String description;
    private boolean canView;
    private boolean canEdit;
    private ModuleResponse module;
}
