package com.mirandasidney.pdv.api.service;

import com.mirandasidney.pdv.api.controller.dto.request.category.CategoryRequestBody;
import com.mirandasidney.pdv.api.controller.dto.response.category.CategoryResponse;
import com.mirandasidney.pdv.api.domain.Category;
import com.mirandasidney.pdv.api.mapper.CategoryMapper;
import com.mirandasidney.pdv.api.repository.CategoryRepository;
import com.mirandasidney.pdv.api.service.interfaces.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryServiceImpl implements CategoryService {

    private static final CategoryMapper mapper = CategoryMapper.INSTANCE;
    private final CategoryRepository repository;

    @Override
    public CategoryResponse save(CategoryRequestBody newCategory) {
        Category category = mapper.toModel(newCategory);
        return mapper.toDto(repository.save(category));
    }
}
