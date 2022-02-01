package com.mirandasidney.pdv.api.controller.dto.request.profile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProfileRequest {

    private Long id;
    private String profileName;
    private String description;
}
