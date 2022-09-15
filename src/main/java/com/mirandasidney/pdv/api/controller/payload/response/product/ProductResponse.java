package com.mirandasidney.pdv.api.controller.payload.response.product;

import com.mirandasidney.pdv.api.controller.payload.response.category.CategoryResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductResponse {

    private UUID uuid;

    private String productName;

    private String costPrice;

    private String salePrice;

    private String amount;

    private String createdAt;

    private String update;

    private CategoryResponse category;

}
