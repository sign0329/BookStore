package com.ll.blogspring.domain.product.cart.repository;


import com.ll.blogspring.domain.member.member.entity.Member;
import com.ll.blogspring.domain.product.product.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByBuyer(Member buyer);
}

