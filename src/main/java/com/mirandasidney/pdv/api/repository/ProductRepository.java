package com.mirandasidney.pdv.api.repository;

import com.mirandasidney.pdv.api.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    default Set<Product> findAllSet() {
        return new HashSet<>(this.findAll());
    }
}
