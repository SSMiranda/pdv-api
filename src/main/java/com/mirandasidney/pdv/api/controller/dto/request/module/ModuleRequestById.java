package com.mirandasidney.pdv.api.controller.dto.request.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ModuleRequestById {
    private Long id;
    private boolean enable;
}
