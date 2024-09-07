package com.victor.rewardmanagement.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record RewardsDto(
        String customerId,
        BigDecimal totalCashback,
        BigDecimal currentBalance
) {
}
