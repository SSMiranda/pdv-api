package com.mirandasidney.pdv.api.mapper;

import com.mirandasidney.pdv.api.controller.dto.request.category.CategoryRequestBody;
import com.mirandasidney.pdv.api.controller.dto.request.product.ProductRequestBody;
import com.mirandasidney.pdv.api.controller.dto.response.product.ProductResponse;
import com.mirandasidney.pdv.api.domain.Category;
import com.mirandasidney.pdv.api.domain.Product;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mappings({
        @Mapping(target = "productId", ignore = true),
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(source = "category", target = "category")
    })
    Product toModel(ProductRequestBody product);

    @Mappings({
            @Mapping(target = "categoryId", ignore = true),
            @Mapping(target = "products", ignore = true),
            @Mapping(source = "category", target = "category")
    })
    Category toCategoryModel(CategoryRequestBody categoryRequest);

    @InheritInverseConfiguration
    ProductResponse toDto(Product product);

}
