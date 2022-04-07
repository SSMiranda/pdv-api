package com.mirandasidney.pdv.api.controller;


import com.mirandasidney.pdv.api.controller.dto.request.category.CategoryPostRequest;
import com.mirandasidney.pdv.api.controller.dto.response.category.CategoryResponse;
import com.mirandasidney.pdv.api.controller.dto.response.category.CategoryWithListProductResponse;
import com.mirandasidney.pdv.api.service.interfaces.ICategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api("REST API SISTEMA PDV")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private ICategoryService service;

    @ApiOperation(value = "Cadastra a categoria de um produto")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryResponse> save(@Valid @RequestBody final CategoryPostRequest category) {
        return service.save(category);
    }

    @ApiOperation(value = "Retorna a lista de categorias")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<CategoryWithListProductResponse>> findAll() {
        return service.findAll();
    }

    @ApiOperation(value = "Busca uma categoria pelo ID")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryWithListProductResponse> findCategoryById(@PathVariable final UUID id) {
        return service.findCategoryById(id);
    }

    @ApiOperation(value = "Remove uma categoria")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable final UUID id) {
        return service.removeCategory(id);
    }

    @ApiOperation(value = "Atualiza uma categoria")
    @PutMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryResponse> update(@PathVariable("id") final UUID id, @RequestBody final CategoryPostRequest category) {
        return service.update(category, id);
    }
}
