package com.mirandasidney.pdv.api.controller.dto.response.user;

import com.mirandasidney.pdv.api.controller.dto.response.profile.ProfileResponse;
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
}
