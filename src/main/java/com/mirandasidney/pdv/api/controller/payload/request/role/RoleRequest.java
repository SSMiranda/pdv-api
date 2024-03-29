package com.mirandasidney.pdv.api.controller.payload.request.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleRequest {
    private String name;
    private String description;
}
