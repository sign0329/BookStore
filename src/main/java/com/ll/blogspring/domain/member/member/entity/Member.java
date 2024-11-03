package com.ll.blogspring.domain.member.member.entity;

import com.ll.blogspring.global.jpa.BaseEntity;
import jakarta.persistence.Entity;
import lombok.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Builder
@AllArgsConstructor(access = PROTECTED)
@NoArgsConstructor(access = PROTECTED)
@Setter
@Getter
@ToString(callSuper = true)
public class Member extends BaseEntity {

    private String username;
    private String password;
    private long restCash;

}
