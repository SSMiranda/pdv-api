package com.mirandasidney.pdv.api.service.interfaces;

import com.mirandasidney.pdv.api.controller.dto.request.category.CategoryRequestBody;
import com.mirandasidney.pdv.api.controller.dto.response.category.CategoryResponse;

public interface CategoryService {

    CategoryResponse save(CategoryRequestBody category);
}
