package com.mirandasidney.pdv.api.controller.payload.request.user;

import com.mirandasidney.pdv.api.controller.payload.request.role.RoleRequestById;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignupRequest {

    @NotNull(message = "Firstname is required")
    private String firstname;

    @NotNull(message = "Lastname is required")
    private String lastname;

    @NotNull(message = "Username is required")
    private String username;

    @NotNull(message = "Password is required")
    private String password;

    @NotNull(message = "Role is required")
    private Set<RoleRequestById> roles = new HashSet<>();

    private String phone;
}
