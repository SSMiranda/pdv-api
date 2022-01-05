package com.mirandasidney.pdv.api.controller.dto.request.profile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProfilePostRequest {

    private Long id;
    private String name;
}
