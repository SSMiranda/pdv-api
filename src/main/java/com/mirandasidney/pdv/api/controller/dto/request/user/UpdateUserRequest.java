package com.mirandasidney.pdv.api.controller.dto.request.user;

import com.mirandasidney.pdv.api.controller.dto.request.role.ProfileUserRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateUserRequest {
    private String firstname;
    private String lastname;
    private String username;
    private ProfileUserRequest role;
    private String phone;
    private Boolean active;
}
