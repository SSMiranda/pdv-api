package com.mirandasidney.pdv.api.controller.dto.request.product;

import com.mirandasidney.pdv.api.controller.dto.request.category.CategoryRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductRequestBody {

    @NotNull
    private String productName;
    private CategoryRequest category;
}
