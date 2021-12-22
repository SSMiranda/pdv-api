package com.mirandasidney.pdv.api.mapper;

import com.mirandasidney.pdv.api.controller.dto.request.category.CategoryRequestBody;
import com.mirandasidney.pdv.api.controller.dto.response.category.CategoryResponse;
import com.mirandasidney.pdv.api.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mappings({
    @Mapping(target = "categoryId", ignore = true),
    @Mapping(target = "products", ignore = true)
    })
    Category toModel(CategoryRequestBody categoryRequest);

    @Mapping(source = "category", target = "category")
    CategoryResponse toDto(Category category);

}
