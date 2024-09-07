package com.victor.rewardmanagement.exception;

public record ValidationError(
        String field,
        String message
) {
}
