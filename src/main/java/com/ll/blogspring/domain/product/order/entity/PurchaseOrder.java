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
    private boolean isPaid; // 결제여부
    private boolean isCanceled; // 취소여부
    private boolean isRefunded; // 환불여부

    public void addItem(CartItem cartItem) {
        OrderItem orderItem = OrderItem.builder()
                .purchaseOrder(this)
                .product(cartItem.getProduct())
                .build();
        orderItems.add(orderItem);
    }
}
