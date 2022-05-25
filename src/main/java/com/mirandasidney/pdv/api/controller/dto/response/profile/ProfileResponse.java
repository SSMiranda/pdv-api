package com.mirandasidney.pdv.api.controller.dto.response.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProfileResponse {
    private UUID uuid;
    private String profileName;
    private String description;
}
