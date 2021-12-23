package com.mirandasidney.pdv.api.controller.dto.request.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryRequestBody {

    @NotNull
    private String name;
}
