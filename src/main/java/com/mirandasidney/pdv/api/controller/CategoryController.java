package com.mirandasidney.pdv.api.controller;


import com.mirandasidney.pdv.api.controller.dto.request.category.CategoryRequestBody;
import com.mirandasidney.pdv.api.controller.dto.request.user.UserPutRequestByUser;
import com.mirandasidney.pdv.api.controller.dto.response.category.CategoryResponse;
import com.mirandasidney.pdv.api.controller.dto.response.user.UserResponse;
import com.mirandasidney.pdv.api.service.CategoryServiceImpl;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@Api("REST API SISTEMA PDV")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private CategoryServiceImpl service;

    @ApiOperation(value = "Cadastra a categoria de um produto")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryResponse> save(@Valid @RequestBody final CategoryRequestBody category) {
        final URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/api/v1/categories/{id}")
                .buildAndExpand(category)
                .toUri();
        return ResponseEntity.created(uri).body(service.save(category));
    }

    @ApiOperation(value = "Retorna a lista de categorias")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CategoryResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @ApiOperation(value = "Busca uma categoria pelo ID")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryResponse> findCategoryById(@PathVariable final Long id) {
        final CategoryResponse category = service.findCategoryById(id);
        if (category == null)
            return ResponseEntity.notFound().build();
        else {
            return ResponseEntity.ok(category);
        }
    }

    @ApiOperation(value = "Remove uma categoria")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable final Long id) {
        return service.removeCategory(id) ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().build();
    }

    @ApiOperation(value = "Atualiza uma categoria")
    @PutMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryResponse> update(@PathVariable("id") final Long id, @RequestBody final CategoryRequestBody category) {
        return ResponseEntity.ok(service.update(category, id));
    }
}
