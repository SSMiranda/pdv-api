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

    @NotNull(message = "product name must not be empty or null")
    private String productName;

    @NotNull(message = "product cost price must not be empty or null")
    private String costPrice;

    @NotNull(message = "product sale price must not be empty or null")
    private String salePrice;

    @NotNull(message = "product amount must not be empty or null")
    private String amount;

    private CategoryRequest category;
}
