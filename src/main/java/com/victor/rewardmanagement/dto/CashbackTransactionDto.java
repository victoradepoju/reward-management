package com.victor.rewardmanagement.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Builder
public record CashbackTransactionDto(
        String transactionId,
        LocalDate transactionDate,
        BigDecimal amountEarned,
        String description
) {
}
