package com.mirandasidney.pdv.api.controller.dto.response.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryResponse {

    private Long categoryId;

    private String name;
}
