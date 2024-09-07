package com.victor.rewardmanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterDto (
        @NotBlank(message = "Name is needed for registration")
        String name,

        @Email(message = "Email is not properly formatted")
        @NotBlank(message = "Email is needed for registration")
        String email,

        @NotBlank(message = "Password is needed for registration")
        String password

) {
}

