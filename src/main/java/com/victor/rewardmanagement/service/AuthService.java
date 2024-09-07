package com.victor.rewardmanagement.service;

import com.victor.rewardmanagement.dto.AuthResponse;
import com.victor.rewardmanagement.dto.LoginDto;
import com.victor.rewardmanagement.dto.RegisterDto;
import com.victor.rewardmanagement.exception.EmailAlreadyExistsException;
import com.victor.rewardmanagement.jwt.JwtService;
import com.victor.rewardmanagement.model.Customer;
import com.victor.rewardmanagement.model.CustomerRewards;
import com.victor.rewardmanagement.repository.CustomerRepository;
import com.victor.rewardmanagement.repository.CustomerRewardsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final CustomerRepository customerRepository;
    private final CustomerRewardsRepository customerRewardsRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public AuthResponse register(RegisterDto registerRequest) {
        validateEmailNotExist(registerRequest.email());

        Customer customer = Customer.builder()
                .name(registerRequest.name())
                .email(registerRequest.email())
                .password(passwordEncoder.encode(registerRequest.password()))
                .build();

        customer = customerRepository.save(customer);

        CustomerRewards rewards = new CustomerRewards();
        rewards.setCustomer(customer);
        rewards.setTotalCashback(BigDecimal.ZERO);
        rewards.setCurrentBalance(BigDecimal.ZERO);
        customerRewardsRepository.save(rewards);

        customer.setCustomerRewards(rewards);
        customerRepository.save(customer);

        String result = authenticate(registerRequest.email(), registerRequest.password(), customer);
        return buildAuthResponse(result, customer, "register");
    }

    @Transactional
    public AuthResponse login(LoginDto loginRequest) {
        var customer = customerRepository.findByEmail(loginRequest.email())
                .orElseThrow(() -> new UsernameNotFoundException("Customer not found"));
        String token = authenticate(loginRequest.email(), loginRequest.password(), customer);


        return buildAuthResponse(token, customer, "login");
    }

    private void validateEmailNotExist(String email) {
        if (customerRepository.findByEmail(email).isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
    }

    private String authenticate(String email, String password, Customer customer) {
        Authentication authenticationRequest = new UsernamePasswordAuthenticationToken(email, password);

        try {
            authenticationManager.authenticate(authenticationRequest);

            return jwtService.generateToken(customer);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("");
        }
    }

    private AuthResponse buildAuthResponse(
            String result,
            Customer customer,
            String path
    ) {
        return AuthResponse.builder()
                .name(customer.getName())
                .email(customer.getEmail())
                .token(result)
                .customerId(customer.getId())
                .message(path.equals("login") ? "Login successful"
                        : "Registration successful")
                .build();
    }
}
