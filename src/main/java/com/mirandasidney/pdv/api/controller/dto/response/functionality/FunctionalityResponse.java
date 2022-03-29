package com.mirandasidney.pdv.api.controller.dto.response.functionality;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FunctionalityResponse {

    private UUID uuid;
    private String name;
    private String description;
    private boolean canView;
    private boolean canEdit;
}
