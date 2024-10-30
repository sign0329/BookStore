package com.ll.blogspring.domain.cash.repository;

import com.ll.blogspring.domain.cash.cash.entity.CashLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashLogRepository extends JpaRepository <CashLog, Long> {

}
