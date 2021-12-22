package com.mirandasidney.pdv.api.controller;


import com.mirandasidney.pdv.api.controller.dto.request.product.ProductRequestBody;
import com.mirandasidney.pdv.api.controller.dto.response.product.ProductResponse;
import com.mirandasidney.pdv.api.service.ProductServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@CrossOrigin(origins = "*")
@RestController
@Api("REST API SISTEMA PDV")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/v1/products")
public class ProductController {

    private ProductServiceImpl service;

    @ApiOperation(value = "Cadastra um produto")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResponse> save(@Valid @RequestBody final ProductRequestBody product) {
        final URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/api/v1/products/{id}")
                .buildAndExpand(product)
                .toUri();
        return ResponseEntity.created(uri).body(service.save(product));
    }
}
