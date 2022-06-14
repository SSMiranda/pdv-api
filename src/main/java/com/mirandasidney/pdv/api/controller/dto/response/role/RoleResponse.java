package com.mirandasidney.pdv.api.controller.dto.response.role;

import com.mirandasidney.pdv.api.domain.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

//@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleResponse {
    private UUID uuid;
    private String role;
    private String description;

    public RoleResponse(Role role) {
        this.uuid = role.getUuid();
        this.role = role.getAuthority();
        this.description = role.getDescription();
    }

}
