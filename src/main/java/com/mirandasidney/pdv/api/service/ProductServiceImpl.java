package com.mirandasidney.pdv.api.service;

import com.mirandasidney.pdv.api.controller.dto.request.product.ProductRequestBody;
import com.mirandasidney.pdv.api.controller.dto.response.product.ProductResponse;
import com.mirandasidney.pdv.api.domain.Product;
import com.mirandasidney.pdv.api.mapper.ProductMapper;
import com.mirandasidney.pdv.api.repository.ProductRepository;
import com.mirandasidney.pdv.api.service.interfaces.ProductService;
import com.mirandasidney.pdv.api.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductServiceImpl implements ProductService {

    private static final ProductMapper mapper = ProductMapper.INSTANCE;
    private ProductRepository repository;

    private void setTime(Product product) {
        product.setCreatedAt(Util.formatDate());
    }

    @Override
    public ProductResponse save(ProductRequestBody product) {
        Product newProduct = mapper.toModel(product);
        setTime(newProduct);
        Product savedProduct = repository.save(newProduct);
        return mapper.toDto(savedProduct);
    }
}
