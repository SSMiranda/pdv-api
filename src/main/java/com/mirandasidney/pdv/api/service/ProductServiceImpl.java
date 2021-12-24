package com.mirandasidney.pdv.api.service;

import com.mirandasidney.pdv.api.controller.dto.request.product.ProductRequestBody;
import com.mirandasidney.pdv.api.controller.dto.response.product.ProductResponse;
import com.mirandasidney.pdv.api.domain.Product;
import com.mirandasidney.pdv.api.mapper.ProductMapper;
import com.mirandasidney.pdv.api.repository.ProductRepository;
import com.mirandasidney.pdv.api.service.interfaces.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductServiceImpl implements IProductService {

    private static final ProductMapper mapper = ProductMapper.INSTANCE;
    private ProductRepository repository;

    @Override
    public ResponseEntity<ProductResponse> save(ProductRequestBody product) {
        final URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/api/v1/products/{id}")
                .buildAndExpand(product)
                .toUri();
        Product savedProduct = repository.save(mapper.toModel(product));

        return ResponseEntity.created(uri).body(mapper.toDto(repository.save(savedProduct)));
    }
}
