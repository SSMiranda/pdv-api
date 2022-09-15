package com.mirandasidney.pdv.api.service.interfaces;

import com.mirandasidney.pdv.api.controller.payload.request.category.CategoryPostRequest;
import com.mirandasidney.pdv.api.controller.payload.response.category.CategoryResponse;
import com.mirandasidney.pdv.api.controller.payload.response.category.CategoryWithListProductResponse;
import org.springframework.http.ResponseEntity;

import java.util.Set;
import java.util.UUID;

public interface ICategoryService {

    ResponseEntity<CategoryResponse> save(CategoryPostRequest category);

    ResponseEntity<Set<CategoryWithListProductResponse>> findAll();

    ResponseEntity<CategoryWithListProductResponse> findCategoryById(UUID id);

    ResponseEntity<?> removeCategory(UUID id);

    ResponseEntity<CategoryResponse> update(CategoryPostRequest category, UUID id);
}
