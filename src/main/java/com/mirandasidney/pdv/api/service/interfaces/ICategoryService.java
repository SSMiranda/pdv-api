package com.mirandasidney.pdv.api.service.interfaces;

import com.mirandasidney.pdv.api.controller.dto.request.category.CategoryPostRequest;
import com.mirandasidney.pdv.api.controller.dto.response.category.CategoryResponse;
import com.mirandasidney.pdv.api.controller.dto.response.category.CategoryWithListProductResponse;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface ICategoryService {

    ResponseEntity<CategoryResponse> save(CategoryPostRequest category);

    ResponseEntity<Set<CategoryWithListProductResponse>> findAll();

    ResponseEntity<CategoryWithListProductResponse> findCategoryById(Long id);

    ResponseEntity<Void> removeCategory(Long id);

    ResponseEntity<CategoryResponse> update(CategoryPostRequest category, Long id);
}
