package com.mirandasidney.pdv.api.controller.payload.response.category;

import com.mirandasidney.pdv.api.controller.payload.response.product.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryWithListProductResponse {

    private UUID uuid;

    private String name;

    private Set<ProductResponse> products;
}
