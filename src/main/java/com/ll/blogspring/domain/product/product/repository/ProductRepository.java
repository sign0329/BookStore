package com.ll.blogspring.domain.product.product.repository;


import com.ll.blogspring.domain.product.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
