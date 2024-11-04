package com.ll.blogspring.domain.product.order.entity;

import com.ll.blogspring.domain.member.member.entity.Member;
import com.ll.blogspring.domain.product.product.entity.CartItem;
import com.ll.blogspring.global.jpa.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static lombok.AccessLevel.PROTECTED;


@Entity
@Builder
@AllArgsConstructor(access = PROTECTED)
@NoArgsConstructor(access = PROTECTED)
@Setter
@Getter
@ToString(callSuper = true)
@Table(name = "purchase_order")
public class PurchaseOrder extends BaseEntity {
    @ManyToOne
    private Member buyer;
    @Builder.Default
    @OneToMany(mappedBy = "order", cascade = ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();
    private LocalDateTime payDate; // 결제날짜
    private LocalDateTime cancelDate; // 취소날짜
    private LocalDateTime refundDate; // 환불날짜

    public void addItem(CartItem cartItem) {
        OrderItem orderItem = OrderItem.builder()
                .purchaseOrder(this)
                .product(cartItem.getProduct())
                .build();
        orderItems.add(orderItem);
    }

    public long calcPayPrice(){
        return orderItems.stream()
                .mapToLong(OrderItem::getPayprice)
                .sum();
    }
    public void setPaymentDone () {
        payDate = LocalDateTime.now();
    }

    public void setOrderItems() {
    }
}
