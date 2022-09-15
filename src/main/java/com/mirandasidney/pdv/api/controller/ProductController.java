package com.mirandasidney.pdv.api.controller;


import com.mirandasidney.pdv.api.controller.payload.request.product.ProductRequestBody;
import com.mirandasidney.pdv.api.controller.payload.response.product.ProductResponse;
import com.mirandasidney.pdv.api.service.interfaces.IProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
@Tag(name = "ProductController", description = "REST API SISTEMA PDV")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/v1/products")
public class ProductController {

    private IProductService service;

    @Operation(description = "Cadastra um produto")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResponse> save(@Valid @RequestBody final ProductRequestBody product) {
        return service.save(product);
    }

    @Operation(description = "Retorna a lista de produtos")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<ProductResponse>> findAll() {
        return service.findAll();
    }

    @Operation(description = "Busca um produto pelo ID")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResponse> findUserById(@PathVariable final UUID id) {
        return service.findProductById(id);
    }

    @Operation(description = "Remove uma categoria")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable final UUID id) {
        return service.removeProduct(id);
    }

    @Operation(description = "Atualiza um produto")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResponse> update(@PathVariable("id") final UUID id, @RequestBody final ProductRequestBody product) {
        return service.update(product, id);
    }

    @Operation(description = "Atualiza dados parciais de um produto")
    @PatchMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResponse> update(@RequestBody final ProductRequestBody product, @PathVariable("id") final UUID id)  {
        return service.productPartlyUpdate(product, id);
    }
}
