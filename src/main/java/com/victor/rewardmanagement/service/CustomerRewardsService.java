package com.victor.rewardmanagement.service;

import com.victor.rewardmanagement.dto.RewardsDto;
import com.victor.rewardmanagement.exception.CustomerNotFoundException;
import com.victor.rewardmanagement.model.CustomerRewards;
import com.victor.rewardmanagement.repository.CustomerRewardsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerRewardsService {
    private final CustomerRewardsRepository customerRewardsRepository;

    public RewardsDto getRewards(String customerId) {
        CustomerRewards rewards = customerRewardsRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        return RewardsDto.builder()
                .customerId(customerId)
                .totalCashback(rewards.getTotalCashback())
                .currentBalance(rewards.getCurrentBalance())
                .build();
    }
}
