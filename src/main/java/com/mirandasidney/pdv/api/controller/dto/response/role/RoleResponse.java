package com.mirandasidney.pdv.api.controller.dto.response.role;

import com.mirandasidney.pdv.api.entities.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@Data
public class RoleResponse {
    private UUID uuid;
    private String name;
    private String description;

    public RoleResponse(Role role) {
        this.uuid = role.getUuid();
        this.name = role.getAuthority().replace("ROLE_", "");
        this.description = role.getDescription();
    }

}
