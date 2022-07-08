package com.mirandasidney.pdv.api.controller.dto.response.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProfileResponseAllAttribute {
    private UUID uuid;
    private String name;
    private String description;
}
