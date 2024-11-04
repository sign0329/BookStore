package com.ll.blogspring.domain.product.order.service;

import com.ll.blogspring.domain.cash.cash.entity.CashLog;
import com.ll.blogspring.domain.member.member.entity.Member;
import com.ll.blogspring.domain.member.service.MemberService;
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
    private final MemberService memberService;

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

    @Transactional
    public void payByCashOnly(PurchaseOrder purchaseOrder){
        Member buyer = purchaseOrder.getBuyer();
        long restCash = buyer.getRestCash();
        long payPrice = purchaseOrder.calcPayPrice();

        if (payPrice > restCash){
            throw new RuntimeException("예치금이 부족합니다");
        }

        memberService.addCash(buyer, payPrice * -1, CashLog.EvenType.사용__예치금_주문결제, purchaseOrder);

        payDone(purchaseOrder);
    }

    private void payDone(PurchaseOrder purchaseOrder) {
        purchaseOrder.setPaymentDone();
    }

    public void refund(PurchaseOrder purchaseOrder){
        long payPrice = purchaseOrder.calcPayPrice();

        memberService.addCash(purchaseOrder.getBuyer(), payPrice, CashLog.EvenType.환불__예치금_주문결제, purchaseOrder);

        purchaseOrder.setCancelDone();
        purchaseOrder.setRefundDone();
    }
}
