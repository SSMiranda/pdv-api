package com.mirandasidney.pdv.api.controller.dto.request.user;

import com.mirandasidney.pdv.api.controller.dto.request.role.RoleRequestById;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserPostRequestBody {
    @NotNull(message = "Firstname can not be null")
    private String firstname;
    @NotNull(message = "Lastname can not be null")
    private String lastname;
    @NotNull(message = "Username can not be null")
    private String username;
    @NotNull(message = "Password can not be null")
    private String password;
    @NotNull
    private Set<RoleRequestById> roles;

    private String phone;
}
