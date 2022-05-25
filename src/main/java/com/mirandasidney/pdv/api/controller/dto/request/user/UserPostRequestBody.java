package com.mirandasidney.pdv.api.controller.dto.request.user;

import com.mirandasidney.pdv.api.controller.dto.request.role.ProfileUserRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserPostRequestBody {
    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private ProfileUserRequest role;
    private String phone;
}
