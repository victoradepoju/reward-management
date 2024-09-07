package com.victor.rewardmanagement.dto;

import com.victor.rewardmanagement.custom_validator.PositiveBigDecimal;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record AwardCashbackDto(
        @PositiveBigDecimal(message = "cashback amount must be a number and more than zero!")
        BigDecimal amount,

        @NotBlank(message = "Description cannot be empty for cashback award")
        String description
) {
}
