package com.mirandasidney.pdv.api.controller.dto.request.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProfileUserRequest {
    private UUID uuid;
}
