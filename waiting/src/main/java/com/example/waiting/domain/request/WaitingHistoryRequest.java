package com.example.waiting.domain.request;

import com.example.waiting.domain.entity.WaitingHistory;
import lombok.Data;

import java.util.UUID;
@Data
public class WaitingHistoryRequest {
    private String storeId;
    private Integer total;

    public WaitingHistory toEntity(String storeId,UUID customerId, String customerName) {
        return WaitingHistory.builder()
                .total(total)
                .storeId(UUID.fromString(storeId))
                .customerId(customerId)
                .customerName(customerName)
                .build();
    }
}
