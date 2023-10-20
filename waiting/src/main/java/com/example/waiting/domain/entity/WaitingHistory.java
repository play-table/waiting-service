package com.example.waiting.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Table(name = "waitingHistory")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class WaitingHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID storeId;
    private UUID customerId;
    private String customerName;
    private Integer total;
}
