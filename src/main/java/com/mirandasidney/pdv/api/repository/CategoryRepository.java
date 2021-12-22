package com.mirandasidney.pdv.api.repository;

import com.mirandasidney.pdv.api.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
