package com.mirandasidney.pdv.api.controller;


import com.mirandasidney.pdv.api.controller.payload.request.category.CategoryPostRequest;
import com.mirandasidney.pdv.api.controller.payload.response.category.CategoryResponse;
import com.mirandasidney.pdv.api.controller.payload.response.category.CategoryWithListProductResponse;
import com.mirandasidney.pdv.api.service.interfaces.ICategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Set;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@Tag(name = "CategoryController" ,description = "REST API SISTEMA PDV")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private ICategoryService service;

    @Operation(description = "Cadastra a categoria de um produto")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryResponse> save(@Valid @RequestBody final CategoryPostRequest category) {
        return service.save(category);
    }

    @Operation(description = "Retorna a lista de categorias")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<CategoryWithListProductResponse>> findAll() {
        return service.findAll();
    }

    @Operation(description = "Busca uma categoria pelo ID")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryWithListProductResponse> findCategoryById(@PathVariable final UUID id) {
        return service.findCategoryById(id);
    }

    @Operation(description = "Remove uma categoria")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable final UUID id) {
        return service.removeCategory(id);
    }

    @Operation(description = "Atualiza uma categoria")
    @PutMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryResponse> update(@PathVariable("id") final UUID id, @RequestBody final CategoryPostRequest category) {
        return service.update(category, id);
    }
}
