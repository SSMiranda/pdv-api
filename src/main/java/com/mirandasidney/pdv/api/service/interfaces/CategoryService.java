package com.mirandasidney.pdv.api.service.interfaces;

import com.mirandasidney.pdv.api.controller.dto.request.category.CategoryRequestBody;
import com.mirandasidney.pdv.api.controller.dto.response.category.CategoryResponse;

import java.util.List;

public interface CategoryService {

    CategoryResponse save(CategoryRequestBody category);
    List<CategoryResponse> findAll();
    CategoryResponse findCategoryById(Long id);
    boolean removeCategory(Long id);
    CategoryResponse update(CategoryRequestBody category, Long id);
}
