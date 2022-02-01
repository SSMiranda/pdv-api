package com.mirandasidney.pdv.api.controller.dto.response.module;

import com.mirandasidney.pdv.api.controller.dto.response.FunctionalityResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ModuleResponse {

    private Long id;
    private boolean isChecked;
    private String name;
    private String description;
    private Set<FunctionalityResponse> functionalities;
}
