package com.mirandasidney.pdv.api.controller.payload.request.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleRequestById {
    private UUID uuid;
}
