package com.mirandasidney.pdv.api.repository;

import com.mirandasidney.pdv.api.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    default Set<Category> findAllSet() {
        return new HashSet<>(this.findAll());
    }
}
