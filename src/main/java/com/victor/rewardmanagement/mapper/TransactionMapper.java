package com.victor.rewardmanagement.mapper;

import com.victor.rewardmanagement.dto.CashbackTransactionDto;
import com.victor.rewardmanagement.model.CashbackTransaction;
import org.springframework.stereotype.Service;

@Service
public class TransactionMapper {
    public CashbackTransactionDto mapToCashbackHistoryDto(
            CashbackTransaction cashbackTransaction
    ) {
        return CashbackTransactionDto.builder()
                .transactionId(cashbackTransaction.getTransactionId())
                .transactionDate(cashbackTransaction.getTransactionDate())
                .amountEarned(cashbackTransaction.getAmountEarned())
                .description(cashbackTransaction.getDescription())
                .build();
    }
}
