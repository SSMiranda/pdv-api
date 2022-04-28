package com.mirandasidney.pdv.api.controller.dto.request.functionality;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FunctionalityRequestById {
    private UUID uuid;
}
