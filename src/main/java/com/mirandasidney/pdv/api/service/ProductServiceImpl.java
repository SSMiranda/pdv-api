package com.mirandasidney.pdv.api.service;

import com.mirandasidney.pdv.api.controller.dto.request.product.ProductRequestBody;
import com.mirandasidney.pdv.api.controller.dto.response.product.ProductResponse;
import com.mirandasidney.pdv.api.domain.Product;
import com.mirandasidney.pdv.api.mapper.ProductMapper;
import com.mirandasidney.pdv.api.repository.ProductRepository;
import com.mirandasidney.pdv.api.service.interfaces.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.Set;

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

    @Override
    public ResponseEntity<Set<ProductResponse>> findAll() {
        return ResponseEntity.ok().body(mapper.toSetDto(repository.findAllSet()));
    }

    @Override
    public ResponseEntity<ProductResponse> findProductById(Long id) {
        return repository.findById(id)
                .map(product -> ResponseEntity.ok().body(mapper.toDto(product)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Void> removeProduct(Long id) {
        Optional<Product> product = repository.findById(id);
        if (product.isPresent()) {
            repository.delete(product.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<ProductResponse> update(ProductRequestBody productUpdate, Long id) {
        return repository.findById(id)
                .map(product -> {
                    BeanUtils.copyProperties(productUpdate, product);
                    repository.save(product);
                    return ResponseEntity.ok().body(mapper.toDto(product));
                }).orElse(ResponseEntity.badRequest().build());
    }

}
