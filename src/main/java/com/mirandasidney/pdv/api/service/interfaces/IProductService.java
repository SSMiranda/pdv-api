package com.mirandasidney.pdv.api.service.interfaces;

import com.mirandasidney.pdv.api.controller.payload.request.product.ProductRequestBody;
import com.mirandasidney.pdv.api.controller.payload.response.product.ProductResponse;
import org.springframework.http.ResponseEntity;

import java.util.Set;
import java.util.UUID;

public interface IProductService {
    ResponseEntity<ProductResponse> save(ProductRequestBody product);

    ResponseEntity<Set<ProductResponse>> findAll();

    ResponseEntity<ProductResponse> findProductById(UUID id);

    ResponseEntity<?> removeProduct(UUID id);

    ResponseEntity<ProductResponse> update(ProductRequestBody product, UUID id);

    ResponseEntity<ProductResponse> productPartlyUpdate(ProductRequestBody product, UUID id);
}
