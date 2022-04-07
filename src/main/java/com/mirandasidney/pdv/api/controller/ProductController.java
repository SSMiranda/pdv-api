package com.mirandasidney.pdv.api.controller;


import com.mirandasidney.pdv.api.controller.dto.request.product.ProductRequestBody;
import com.mirandasidney.pdv.api.controller.dto.response.product.ProductResponse;
import com.mirandasidney.pdv.api.service.interfaces.IProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api("REST API SISTEMA PDV")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/v1/products")
public class ProductController {

    private IProductService service;

    @ApiOperation(value = "Cadastra um produto")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResponse> save(@Valid @RequestBody final ProductRequestBody product) {
        return service.save(product);
    }

    @ApiOperation(value = "Retorna a lista de produtos")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<ProductResponse>> findAll() {
        return service.findAll();
    }

    @ApiOperation(value = "Busca um produto pelo ID")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResponse> findUserById(@PathVariable final UUID id) {
        return service.findProductById(id);
    }

    @ApiOperation(value = "Remove uma categoria")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable final UUID id) {
        return service.removeProduct(id);
    }

    @ApiOperation(value = "Atualiza um produto")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResponse> update(@PathVariable("id") final UUID id, @RequestBody final ProductRequestBody product) {
        return service.update(product, id);
    }

    @ApiOperation(value = "Atualiza dados parciais de um produto")
    @PatchMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResponse> update(@RequestBody final ProductRequestBody product, @PathVariable("id") final UUID id)  {
        return service.productPartlyUpdate(product, id);
    }
}
