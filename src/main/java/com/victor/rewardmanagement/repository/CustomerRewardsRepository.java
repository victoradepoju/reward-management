package com.victor.rewardmanagement.repository;

import com.victor.rewardmanagement.model.CustomerRewards;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRewardsRepository extends JpaRepository<CustomerRewards, String> {
    Optional<CustomerRewards> findByCustomerId(String customerId);
}
