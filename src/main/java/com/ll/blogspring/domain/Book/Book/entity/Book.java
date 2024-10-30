package com.ll.blogspring.domain.Book.Book.entity;

import com.ll.blogspring.domain.member.member.entity.Member;
import com.ll.blogspring.domain.product.product.entity.Product;
import com.ll.blogspring.global.jpa.BaseEntity;
import com.ll.blogspring.standard.util.Ut;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
@Getter
@ToString(callSuper = true)
public class Book extends BaseEntity {

    @ManyToOne
    private Member author;
    @OneToOne
    private Product product;
    private String title;
    private String body;
    private int price;


    public String getModeName() {
        return Ut.str.lcfirst(this.getClass().getSimpleName());
    }
}
