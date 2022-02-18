package com.mirandasidney.pdv.api.controller.dto.request.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ModuleRequestById {
    private UUID id;
}
