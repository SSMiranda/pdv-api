package com.mirandasidney.pdv.api.service;

import com.mirandasidney.pdv.api.controller.dto.request.category.CategoryRequestBody;
import com.mirandasidney.pdv.api.controller.dto.response.category.CategoryResponse;
import com.mirandasidney.pdv.api.controller.dto.response.category.CategoryWithListProductResponse;
import com.mirandasidney.pdv.api.domain.Category;
import com.mirandasidney.pdv.api.mapper.CategoryMapper;
import com.mirandasidney.pdv.api.repository.CategoryRepository;
import com.mirandasidney.pdv.api.service.interfaces.CategoryService;
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
public class CategoryServiceImpl implements CategoryService {

    private static final CategoryMapper mapper = CategoryMapper.INSTANCE;
    private final CategoryRepository repository;

    @Override
    public ResponseEntity<CategoryResponse> save(CategoryRequestBody newCategory) {
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/api/v1/categories/{id}")
                .buildAndExpand(newCategory)
                .toUri();

        Category category = mapper.toModel(newCategory);

        return ResponseEntity.created(uri).body(mapper.toDto(repository.save(category)));
    }

    @Override
    public  ResponseEntity<Set<CategoryWithListProductResponse>> findAll() {
        return ResponseEntity.ok().body(mapper.toCategoryListDto(repository.findAllSet()));
    }

    @Override
    public ResponseEntity<CategoryWithListProductResponse> findCategoryById(Long id) {
        return repository.findById(id)
                .map(category -> ResponseEntity.ok().body(mapper.toCategoryListDto(category)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Void> removeCategory(Long id) {
        Optional<Category> category = repository.findById(id);
        if (category.isPresent()) {
            repository.delete(category.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<CategoryResponse> update(CategoryRequestBody categoryRequest, Long id) {
        return repository.findById(id)
                .map(category -> {
                    BeanUtils.copyProperties(categoryRequest, category);
                    repository.save(category);
                    return ResponseEntity.ok().body(mapper.toDto(category));
                }).orElse(ResponseEntity.badRequest().build());
    }


}
