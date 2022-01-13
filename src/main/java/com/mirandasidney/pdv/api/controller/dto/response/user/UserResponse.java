package com.mirandasidney.pdv.api.controller.dto.response.user;

import com.mirandasidney.pdv.api.controller.dto.response.profile.ProfileResponse;
import com.mirandasidney.pdv.api.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponse {
    private Long userId;
    private String firstname;
    private String lastname;
    private String username;
    private String phone;
    private ProfileResponse profile;
    private String createdAt;
    private Boolean status;

    public UserResponse(User user) {
        this.userId = user.getUserId();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.username = user.getUsername();
        this.phone = user.getPhone();
        this.profile = new ProfileResponse(user.getProfile().getId(), user.getProfile().getName());
        this.createdAt = user.getCreatedAt();
        this.status = user.getStatus();
    }

}
