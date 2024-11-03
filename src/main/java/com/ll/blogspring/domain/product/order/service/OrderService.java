package com.ll.blogspring.domain.product.order.service;

import com.ll.blogspring.domain.member.member.entity.Member;
import com.ll.blogspring.domain.product.cart.service.CartService;
import com.ll.blogspring.domain.product.order.entity.PurchaseOrder;
import com.ll.blogspring.domain.product.order.repository.PurchaseOrderRepository;
import com.ll.blogspring.domain.product.product.entity.CartItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final CartService cartService;

    @Transactional
    public PurchaseOrder createFromCart(Member buyer) {
        List<CartItem> cartItems = cartService.findItemsByBuyer(buyer);
        PurchaseOrder purchaseOrder = PurchaseOrder.builder()
                .buyer(buyer)
                .build();

        cartItems.stream()
                .forEach(purchaseOrder::addItem);

        purchaseOrderRepository.save(purchaseOrder);

        cartItems.stream()
                .forEach(cartService::delete);

        return purchaseOrder;
    }
}
