package com.mirandasidney.pdv.api.controller.payload.request.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryRequest {

    private UUID uuid;
}
