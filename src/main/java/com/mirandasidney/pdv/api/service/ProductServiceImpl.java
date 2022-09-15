package com.mirandasidney.pdv.api.service;

import com.mirandasidney.pdv.api.controller.payload.request.product.ProductRequestBody;
import com.mirandasidney.pdv.api.controller.payload.response.product.ProductResponse;
import com.mirandasidney.pdv.api.entities.Category;
import com.mirandasidney.pdv.api.entities.Product;
import com.mirandasidney.pdv.api.exception.ResourceNotFoundException;
import com.mirandasidney.pdv.api.mapper.ProductMapper;
import com.mirandasidney.pdv.api.repository.CategoryRepository;
import com.mirandasidney.pdv.api.repository.ProductRepository;
import com.mirandasidney.pdv.api.service.interfaces.IProductService;
import com.mirandasidney.pdv.api.utils.DateUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductServiceImpl implements IProductService {

    private static final ProductMapper mapper = ProductMapper.INSTANCE;
    private ProductRepository repository;
    private CategoryRepository categoryRepository;

    @Override
    public ResponseEntity<ProductResponse> save(ProductRequestBody product) {
        Optional<Category> category = Optional.ofNullable(categoryRepository
                .findById(product.getCategory().getUuid())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with UUID: " + product.getCategory().getUuid())));

        final URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/api/v1/products/{id}")
                .buildAndExpand(product)
                .toUri();

            Product newProduct = mapper.toModel(product);
            newProduct.setCategory(category.get());
            Product savedProduct = repository.save(newProduct);
            return ResponseEntity.created(uri).body(mapper.toDto(repository.save(savedProduct)));
    }

    @Override
    public ResponseEntity<Set<ProductResponse>> findAll() {
        Set<Product> list = repository.findAllSet();
        if(list.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok().body(mapper.toSetDto(list));
    }

    @Override
    public ResponseEntity<ProductResponse> findProductById(UUID id) {
        return repository.findById(id)
                .map(product -> ResponseEntity.ok().body(mapper.toDto(product)))
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with UUID: " + id));
    }

    @Override
    public ResponseEntity<?> removeProduct(UUID id) {
        return repository.findById(id)
                .map(product -> {
                    repository.deleteById(id);
                   return ResponseEntity.noContent().build();
                })
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with UUID: " + id));
    }

    @Override
    public ResponseEntity<ProductResponse> update(ProductRequestBody productUpdate, UUID id) {
        return repository.findById(id)
                .map(product -> {
                    BeanUtils.copyProperties(productUpdate, product);
                    repository.save(product);
                    return ResponseEntity.ok().body(mapper.toDto(product));
                }).orElseThrow(() -> new ResourceNotFoundException("Product not found with UUID: " + id));
    }

    @Override
    @Transactional
    public ResponseEntity<ProductResponse> productPartlyUpdate(ProductRequestBody productUpdate, UUID id) {
        return repository.findById(id).map(p -> {
                if(productUpdate.getProductName() != null) p.setProductName(productUpdate.getProductName());
                if(productUpdate.getCategory() != null){
                    final Optional<Category> category = categoryRepository.findById(productUpdate.getCategory().getUuid());
                    if(category.isPresent())
                        p.setCategory(category.get());
                }
                if(productUpdate.getCostPrice() != null) p.setCostPrice(new BigDecimal(productUpdate.getCostPrice()));
                if(productUpdate.getSalePrice() != null) p.setSalePrice(new BigDecimal(productUpdate.getSalePrice()));
                if(productUpdate != null) p.setUpdate(DateUtils.getDateTime());

                repository.save(p);
                return ResponseEntity.ok().body(mapper.toDto(p));
            }).orElseThrow(() -> new ResourceNotFoundException("Product not found with UUID: " + id));
    }

}
