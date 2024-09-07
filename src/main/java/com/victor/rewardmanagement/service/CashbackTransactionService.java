package com.victor.rewardmanagement.service;

import com.victor.rewardmanagement.dto.AwardCashbackDto;
import com.victor.rewardmanagement.dto.CashbackTransactionDto;
import com.victor.rewardmanagement.exception.CustomerNotFoundException;
import com.victor.rewardmanagement.mapper.TransactionMapper;
import com.victor.rewardmanagement.model.CashbackTransaction;
import com.victor.rewardmanagement.model.CustomerRewards;
import com.victor.rewardmanagement.repository.CashbackTransactionRepository;
import com.victor.rewardmanagement.repository.CustomerRewardsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CashbackTransactionService {
    private final CustomerRewardsRepository customerRewardsRepository;
    private final CashbackTransactionRepository cashbackTransactionRepository;
    private final TransactionMapper mapper;

    public List<CashbackTransactionDto> getRewards(String customerId) {
        CustomerRewards rewards = customerRewardsRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        List<CashbackTransaction> transactions = rewards.getCashbackTransactions();

        return transactions.stream()
                .map(mapper::mapToCashbackHistoryDto)
                .toList();
    }

    @Transactional
    public String awardCustomerCashback(
            String customerId,
            AwardCashbackDto awardCashbackDto
    ) {
        CustomerRewards rewards = customerRewardsRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        String customerName = rewards.getCustomer().getName();

        BigDecimal amountToAdd = awardCashbackDto.amount();
        BigDecimal newAmount = rewards.getTotalCashback().add(amountToAdd);

        rewards.setTotalCashback(newAmount);

        CashbackTransaction transaction = new CashbackTransaction();
        transaction.setTransactionDate(LocalDate.now());
        transaction.setAmountEarned(amountToAdd);
        transaction.setDescription(awardCashbackDto.description());
        transaction.setCustomerRewards(rewards);

        rewards.getCashbackTransactions().add(transaction);

        customerRewardsRepository.save(rewards);
        cashbackTransactionRepository.save(transaction);
        return customerName + " has been awarded " + amountToAdd + " cashbacks!";
    }
}
