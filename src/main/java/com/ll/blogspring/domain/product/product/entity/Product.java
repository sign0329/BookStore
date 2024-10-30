package com.ll.blogspring.domain.product.product.entity;

import com.ll.blogspring.domain.member.member.entity.Member;
import com.ll.blogspring.global.jpa.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
@Getter
@ToString(callSuper = true)
public class Product extends BaseEntity {
    @ManyToOne
    private Member author;
    private String relTypeCode;
    private long relId;
    private String name;
    private int price;

}
