package com.ll.blogspring.domain.product.cart.repository;


import com.ll.blogspring.domain.product.product.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}

