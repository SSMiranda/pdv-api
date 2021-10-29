package com.mirandasidney.pdv.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserPostRequestBody {
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String phone;
}
