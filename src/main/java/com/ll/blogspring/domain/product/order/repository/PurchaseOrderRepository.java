package com.ll.blogspring.domain.product.order.repository;

import com.ll.blogspring.domain.product.order.entity.PurchaseOrder;
import jakarta.persistence.criteria.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long>{
}
