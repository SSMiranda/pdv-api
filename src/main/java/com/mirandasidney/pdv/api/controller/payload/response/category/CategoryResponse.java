package com.mirandasidney.pdv.api.controller.payload.response.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryResponse {

    private UUID uuid;

    private String name;
}
