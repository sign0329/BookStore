package com.ll.blogspring.domain.product.cart.service;

import com.ll.blogspring.domain.member.member.entity.Member;
import com.ll.blogspring.domain.product.cart.repository.CartItemRepository;
import com.ll.blogspring.domain.product.product.entity.CartItem;
import com.ll.blogspring.domain.product.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartService {
    private final CartItemRepository cartItemRepository;

    @Transactional
    public CartItem addItem(Member member, Product product) {
        CartItem cartItem = CartItem.builder().
                member(member)
                .product(product)
                .build();
        cartItemRepository.save(cartItem);
        return cartItem;
    }
}
