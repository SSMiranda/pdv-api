package com.mirandasidney.pdv.api.mapper;

import com.mirandasidney.pdv.api.controller.payload.request.product.ProductRequestBody;
import com.mirandasidney.pdv.api.controller.payload.response.product.ProductResponse;
import com.mirandasidney.pdv.api.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product toModel(ProductRequestBody product);

    ProductResponse toDto(Product product);

    Set<ProductResponse> toSetDto(Set<Product> product);

}
