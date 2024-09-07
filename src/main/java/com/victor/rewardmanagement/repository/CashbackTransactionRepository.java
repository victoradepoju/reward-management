package com.victor.rewardmanagement.repository;

import com.victor.rewardmanagement.model.CashbackTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashbackTransactionRepository extends JpaRepository<CashbackTransaction, String> {
}
