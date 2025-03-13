package com.crjoua.projerp.repositories;

import com.crjoua.projerp.models.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}