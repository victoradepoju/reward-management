package com.victor.rewardmanagement.controller;

import com.victor.rewardmanagement.dto.AwardCashbackDto;
import com.victor.rewardmanagement.dto.CashbackTransactionDto;
import com.victor.rewardmanagement.service.CashbackTransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/rewards")
@RequiredArgsConstructor
public class CashbackTransactionController {
    private final CashbackTransactionService cashbackTransactionService;

    @GetMapping("/history/{customerId}")
    public ResponseEntity<List<CashbackTransactionDto>> getRewards(
            @PathVariable("customerId") String customerId
    ) {
        return ResponseEntity.ok(cashbackTransactionService.getRewards(customerId));
    }

    @PostMapping("/award-cashback/{customerId}")
    public ResponseEntity<String> awardCashback(
            @PathVariable("customerId") String customerId,
            @RequestBody @Valid AwardCashbackDto awardCashbackDto
    ) {
        return ResponseEntity.ok(cashbackTransactionService
                .awardCustomerCashback(customerId, awardCashbackDto));
    }
}
