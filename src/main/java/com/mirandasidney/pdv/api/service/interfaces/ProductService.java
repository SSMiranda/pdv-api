package com.mirandasidney.pdv.api.service.interfaces;

import com.mirandasidney.pdv.api.controller.dto.request.product.ProductRequestBody;
import com.mirandasidney.pdv.api.controller.dto.response.product.ProductResponse;

public interface ProductService {
    ProductResponse save(ProductRequestBody product);
}
