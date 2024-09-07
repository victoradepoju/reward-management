package com.victor.rewardmanagement.custom_validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BigDecimalValidator.class)
public @interface PositiveBigDecimal {
    String message() default "Value must be positive";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}