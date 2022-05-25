package com.mirandasidney.pdv.api.controller.dto.request.role;

import com.mirandasidney.pdv.api.controller.dto.request.functionality.FunctionalityRequestById;
import com.mirandasidney.pdv.api.controller.dto.request.module.ModuleRequestById;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProfileRequest {
    private String profileName;
    private String description;
    private ModuleRequestById module;
    private FunctionalityRequestById functionality;
}
