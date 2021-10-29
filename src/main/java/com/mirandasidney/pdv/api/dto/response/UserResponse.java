package com.mirandasidney.pdv.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserResponse {
    private Long id;
    private String firstname;
    private String lastname;
    private String username;
    private String phone;
    private String profile;
    private String createdAt;
}
