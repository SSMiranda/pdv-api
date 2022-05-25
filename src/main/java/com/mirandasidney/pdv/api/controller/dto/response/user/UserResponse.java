package com.mirandasidney.pdv.api.controller.dto.response.user;

import com.mirandasidney.pdv.api.controller.dto.response.role.ProfileResponse;
import com.mirandasidney.pdv.api.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponse {
    private UUID uuid;
    private String firstname;
    private String lastname;
    private String username;
    private String phone;
    private ProfileResponse role;
    private String createdAt;
    private Boolean active;
    private String updated;
//    private Set<ModuleResponse> modules;
//    private Set<FunctionalityResponse> functionalities;

    public UserResponse(User user) {
        this.uuid = user.getUuid();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.username = user.getUsername();
        this.phone = user.getPhone();
//        this.role = new ProfileResponse(user.getRoles().getUuid(), user.getRole().getProfileName(), user.getRole().getDescription());
        this.createdAt = user.getCreatedAt();
        this.active = user.getActive();
    }

}
