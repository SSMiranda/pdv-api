package com.mirandasidney.pdv.api.service.interfaces;

import com.mirandasidney.pdv.api.controller.dto.request.category.CategoryRequestBody;
import com.mirandasidney.pdv.api.controller.dto.response.category.CategoryResponse;
import com.mirandasidney.pdv.api.controller.dto.response.category.CategoryWithListProductResponse;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface ICategoryService {

    ResponseEntity<CategoryResponse> save(CategoryRequestBody category);

    ResponseEntity<Set<CategoryWithListProductResponse>> findAll();

    ResponseEntity<CategoryWithListProductResponse> findCategoryById(Long id);

    ResponseEntity<Void> removeCategory(Long id);

    ResponseEntity<CategoryResponse> update(CategoryRequestBody category, Long id);
}
