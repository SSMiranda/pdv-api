package com.mirandasidney.pdv.api.mapper;

import com.mirandasidney.pdv.api.controller.payload.request.category.CategoryPostRequest;
import com.mirandasidney.pdv.api.controller.payload.response.category.CategoryResponse;
import com.mirandasidney.pdv.api.controller.payload.response.category.CategoryWithListProductResponse;
import com.mirandasidney.pdv.api.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);


    Category toModel(CategoryPostRequest categoryRequest);

    CategoryResponse toDto(Category category);

    Set<CategoryWithListProductResponse> toCategoryListDto(Set<Category> categories);

    CategoryWithListProductResponse toCategoryListDto(Category category);

}
