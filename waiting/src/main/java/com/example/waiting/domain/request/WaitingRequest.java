package com.example.waiting.domain.request;

import com.example.waiting.domain.entity.Waiting;
import com.example.waiting.domain.entity.WaitingStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class WaitingRequest {
    private Integer adult;
    private Integer kid;
    private Integer total;
    private String storeId;
    private String customerId;
    private String customerName;
    private String status;

    public Waiting toEntity(String storeId) {
        return Waiting.builder()
                .adult(adult)
                .kid(kid)
                .total(adult+kid)
                .storeId(UUID.fromString(storeId))
                .customerId(UUID.fromString(customerId))
                .customerName(customerName)
                .status(WaitingStatus.valueOf(status))
                .build();
    }
}

