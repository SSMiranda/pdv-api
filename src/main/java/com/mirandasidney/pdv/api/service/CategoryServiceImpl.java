package com.mirandasidney.pdv.api.service;

import com.mirandasidney.pdv.api.controller.dto.request.category.CategoryRequestBody;
import com.mirandasidney.pdv.api.controller.dto.response.category.CategoryResponse;
import com.mirandasidney.pdv.api.domain.Category;
import com.mirandasidney.pdv.api.mapper.CategoryMapper;
import com.mirandasidney.pdv.api.repository.CategoryRepository;
import com.mirandasidney.pdv.api.service.interfaces.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryServiceImpl implements CategoryService {

    private static final CategoryMapper mapper = CategoryMapper.INSTANCE;
    private final CategoryRepository repository;


    private Category findById(Long id) {
        Optional<Category> category = repository.findById(id);
        return category.orElse(null);
    }

    @Override
    public CategoryResponse save(CategoryRequestBody newCategory) {
        Category category = mapper.toModel(newCategory);
        return mapper.toDto(repository.save(category));
    }

    @Override
    public List<CategoryResponse> findAll() {
        return mapper.toCategoryListDto(repository.findAll());
    }

    @Override
    public CategoryResponse findCategoryById(Long id) {
        Category category = findById(id);
        return mapper.toDto(category);
    }

    @Override
    public boolean removeCategory(Long id) {
        Category category = findById(id);
        if(category != null) {
            repository.delete(category);
            return true;
        }
        return false;
    }

    @Override
    public CategoryResponse update(CategoryRequestBody categoryRequest, Long id) {
        Category category = findById(id);
        BeanUtils.copyProperties(categoryRequest, category);
        repository.save(category);
        return mapper.toDto(category);
    }


}
