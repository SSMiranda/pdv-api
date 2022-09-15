package com.mirandasidney.pdv.api.controller.payload.request.product;

import com.mirandasidney.pdv.api.controller.payload.request.category.CategoryRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductRequestBody {

    @NotNull(message = "product name must not be empty or null")
    private String productName;

    private String description;

    @NotNull(message = "product cost price must not be empty or null")
    private String costPrice;

    @NotNull(message = "product sale price must not be empty or null")
    private String salePrice;

    private CategoryRequest category;

}
