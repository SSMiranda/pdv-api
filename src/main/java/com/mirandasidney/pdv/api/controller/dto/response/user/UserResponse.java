package com.mirandasidney.pdv.api.controller.dto.response.user;

import com.mirandasidney.pdv.api.controller.dto.response.profile.ProfileResponse;
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
    private ProfileResponse profile;
    private String createdAt;
    private Boolean status;

    public UserResponse(User user) {
        this.uuid = user.getUuid();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.username = user.getUsername();
        this.phone = user.getPhone();
        this.profile = new ProfileResponse(user.getProfile().getUuid(), user.getProfile().getProfileName(), user.getProfile().getDescription());
        this.createdAt = user.getCreatedAt();
        this.status = user.getStatus();
    }

}
