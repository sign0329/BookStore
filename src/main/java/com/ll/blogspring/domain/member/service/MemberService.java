package com.ll.blogspring.domain.member.service;

import com.ll.blogspring.domain.cash.cash.entity.CashLog;
import com.ll.blogspring.domain.cash.repository.CashLogRepository;
import com.ll.blogspring.domain.cash.service.CashLogService;
import com.ll.blogspring.domain.member.member.entity.Member;
import com.ll.blogspring.domain.member.repository.MemberRepository;
import com.ll.blogspring.global.jpa.BaseEntity;
import com.ll.blogspring.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final CashLogService cashLogService;
    private final CashLogRepository cashLogRepository;

    @Transactional
    public RsData<Member> join(String username, String password){
        Member member = Member.builder()
                .build();

        memberRepository.save(member);

        return  RsData.of("200","회원가입 성공", member);
    }
    public Optional<Member> findByUsername(String usernmae){
        return memberRepository.findByUsername(usernmae);
    }

    @Transactional
    public  void addCash(Member member, long price, CashLog.EvenType evenType, BaseEntity relEntity){
        CashLog cashLog = cashLogService.addCash(member, price, evenType, relEntity);
    }

}
