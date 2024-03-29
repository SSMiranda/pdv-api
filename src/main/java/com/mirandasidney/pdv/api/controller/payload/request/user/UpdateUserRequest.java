package com.mirandasidney.pdv.api.controller.payload.request.user;

import com.mirandasidney.pdv.api.controller.payload.request.role.RoleRequestById;
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
    private RoleRequestById role;
    private String phone;
    private Boolean active;
}
