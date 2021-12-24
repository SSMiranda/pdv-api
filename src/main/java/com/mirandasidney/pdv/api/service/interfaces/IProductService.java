package com.mirandasidney.pdv.api.service.interfaces;

import com.mirandasidney.pdv.api.controller.dto.request.product.ProductRequestBody;
import com.mirandasidney.pdv.api.controller.dto.response.product.ProductResponse;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface IProductService {
    ResponseEntity<ProductResponse> save(ProductRequestBody product);

    ResponseEntity<Set<ProductResponse>> findAll();

    ResponseEntity<ProductResponse> findProductById(Long id);

    ResponseEntity<Void> removeProduct(Long id);

    ResponseEntity<ProductResponse> update(ProductRequestBody product, Long id);
}
