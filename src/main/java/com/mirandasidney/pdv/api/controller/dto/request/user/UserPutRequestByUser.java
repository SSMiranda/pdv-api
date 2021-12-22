package com.mirandasidney.pdv.api.controller.dto.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserPutRequestByUser {
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String phone;
}
