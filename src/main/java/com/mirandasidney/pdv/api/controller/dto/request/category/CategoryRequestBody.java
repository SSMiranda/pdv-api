package com.mirandasidney.pdv.api.controller.dto.request.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryRequestBody {

    private Long id;
    private String name;
}