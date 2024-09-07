package com.victor.rewardmanagement.controller;

import com.victor.rewardmanagement.dto.RewardsDto;
import com.victor.rewardmanagement.service.CustomerRewardsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rewards")
@RequiredArgsConstructor
public class CustomerRewardsController {
    private final CustomerRewardsService customerRewardsService;

    @GetMapping("/balance/{customerId}")
    public ResponseEntity<RewardsDto> getRewards(
            @PathVariable("customerId") String customerId
    ) {
        return ResponseEntity.ok(customerRewardsService.getRewards(customerId));
    }
}
