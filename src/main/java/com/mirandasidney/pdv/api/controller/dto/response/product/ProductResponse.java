package com.mirandasidney.pdv.api.controller.dto.response.product;

import com.mirandasidney.pdv.api.controller.dto.response.category.CategoryResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductResponse {

    private Long productId;

    private String productName;

    private String createdAt;

    private CategoryResponse category;
}
