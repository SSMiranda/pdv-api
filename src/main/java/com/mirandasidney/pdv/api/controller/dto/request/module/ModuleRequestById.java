package com.mirandasidney.pdv.api.controller.dto.request.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ModuleRequestById {
    private Long id;
}
