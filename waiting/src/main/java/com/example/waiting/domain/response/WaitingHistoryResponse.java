package com.example.waiting.domain.response;

import com.example.waiting.domain.entity.WaitingHistory;
import lombok.Getter;

import java.util.UUID;
@Getter
public class WaitingHistoryResponse {
    private UUID id;
    private Integer total;
    private UUID storeId;
    private UUID customerId;
    private String customerName;

    public WaitingHistoryResponse(WaitingHistory waitingHistory) {
        this.id = waitingHistory.getId();
        this.total = waitingHistory.getTotal();
        this.storeId = waitingHistory.getStoreId();
        this.customerId = waitingHistory.getCustomerId();
        this.customerName = waitingHistory.getCustomerName();
    }
}
