package com.mirandasidney.pdv.api.controller.dto.response.profile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProfileResponse {

    private UUID id;
    private String profileName;
    private String description;
}
