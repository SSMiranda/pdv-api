package com.mirandasidney.pdv.api.repository;

import com.mirandasidney.pdv.api.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.stream.Collectors;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    default Set<Product> findAllSet() {
        return this.findAll().stream().collect(Collectors.toSet());
    }
}
