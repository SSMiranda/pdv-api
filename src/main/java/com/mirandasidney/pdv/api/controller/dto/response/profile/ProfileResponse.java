package com.mirandasidney.pdv.api.controller.dto.response.profile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProfileResponse {

    private Long id;
    private String profileName;
    private String description;
}
