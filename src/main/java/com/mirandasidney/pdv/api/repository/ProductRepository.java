package com.mirandasidney.pdv.api.repository;

import com.mirandasidney.pdv.api.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
