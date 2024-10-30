package com.ll.blogspring.domain.cash.service;

import com.ll.blogspring.domain.cash.cash.entity.CashLog;
import com.ll.blogspring.domain.cash.repository.CashLogRepository;
import com.ll.blogspring.domain.member.member.entity.Member;
import com.ll.blogspring.global.jpa.BaseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CashLogService {
    private final CashLogRepository cashLogRepository;

    @Transactional
    public CashLog addCash(Member member, long price, CashLog.EvenType eventType, BaseEntity relEntity) {
        CashLog cashLog = CashLog.builder()
                .member(member)
                .price(price)
                .relTypeCode(relEntity.getModelName())
                .relId(relEntity.getId())
                .eventType(eventType)
                .build();
        cashLogRepository.save(cashLog);
        return cashLog;
    }
}
