package com.mirandasidney.pdv.api.service.interfaces;

import com.mirandasidney.pdv.api.controller.dto.request.product.ProductRequestBody;
import com.mirandasidney.pdv.api.controller.dto.response.product.ProductResponse;
import org.springframework.http.ResponseEntity;

public interface IProductService {
    ResponseEntity<ProductResponse> save(ProductRequestBody product);
}
