package com.mirandasidney.pdv.api.controller.dto.response.category;

import com.mirandasidney.pdv.api.controller.dto.response.product.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryWithListProductResponse {

    private Long categoryId;

    private String name;

    private Set<ProductResponse> products;
}
