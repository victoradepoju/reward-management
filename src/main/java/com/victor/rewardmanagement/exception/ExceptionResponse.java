package com.victor.rewardmanagement.exception;

import java.util.List;

public record ExceptionResponse(
        List<ValidationError> errors
) {
}
