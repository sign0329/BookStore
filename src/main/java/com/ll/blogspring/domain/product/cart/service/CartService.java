package com.ll.blogspring.domain.product.cart.service;

import com.ll.blogspring.domain.member.member.entity.Member;
import com.ll.blogspring.domain.product.cart.repository.CartItemRepository;
import com.ll.blogspring.domain.product.product.entity.CartItem;
import com.ll.blogspring.domain.product.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartService {
    private final CartItemRepository cartItemRepository;

    @Transactional
    public CartItem addItem(Member buyer, Product product) {
        CartItem cartItem = CartItem.builder()
                .buyer(buyer)
                .product(product)
                .build();

        cartItemRepository.save(cartItem);

        return cartItem;
    }
    public List<CartItem> findItemsByBuyer(Member buyer) {
        return cartItemRepository.findByBuyer(buyer);
    }
    public void delete(CartItem cartItem) {
        cartItemRepository.delete(cartItem);
    }
}
