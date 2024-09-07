package com.victor.rewardmanagement.exception;

public record AuthErrorResponse (
        String status,
        String message,
        Integer statusCode
) {
}
