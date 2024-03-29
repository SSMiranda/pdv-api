package com.mirandasidney.pdv.api.controller.payload.response.user;

import com.mirandasidney.pdv.api.controller.payload.response.role.RoleResponse;
import com.mirandasidney.pdv.api.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponse {
    private UUID uuid;
    private String firstname;
    private String lastname;
    private String username;
    private String phone;
    private Set<RoleResponse> roles;
    private String createdAt;
    private Boolean active;
    private String updated;

    public UserResponse(User user) {
        this.uuid = user.getUuid();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.username = user.getUsername();
        this.phone = user.getPhone();
        this.roles = streamCollection(user);
        this.createdAt = user.getCreatedAt();
        this.active = user.getActive();
        this.updated = getUpdated();
    }

    private Set<RoleResponse> streamCollection(User user) {
        return user.getRoles().stream().map(RoleResponse::new).collect(Collectors.toSet());
    }

}
