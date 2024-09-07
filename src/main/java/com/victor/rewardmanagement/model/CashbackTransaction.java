package com.victor.rewardmanagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cashback_transactions")
public class CashbackTransaction {
    @Id
    @Column(updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String transactionId;

    private LocalDate transactionDate;

    private BigDecimal amountEarned;

    private String description;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerRewards customerRewards;
}
