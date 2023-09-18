package com.example.waiting.domain.request;

import com.example.waiting.domain.entity.Waiting;
import com.example.waiting.domain.entity.WaitingStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class WaitingRequest {
    private Integer adult;
    private Integer kid;
    private String storeId;
    private String status;

    public Waiting toEntity(String storeId,UUID customerId, String customerName) {
        return Waiting.builder()
                .adult(adult)
                .kid(kid)
                .total(adult+kid)
                .storeId(UUID.fromString(storeId))
                .customerId(customerId)
                .customerName(customerName)
                .status(WaitingStatus.valueOf(status))
                .build();
    }
}

