package com.example.waiting.domain.response;

import com.example.waiting.domain.entity.Waiting;
import com.example.waiting.domain.entity.WaitingStatus;
import lombok.Getter;

import java.util.UUID;

@Getter
public class WaitingResponse {
    private Long id;
    private Integer adult;
    private Integer kid;
    private Integer total;
    private UUID store_id;
    private UUID customer_id;
    private String customer_name;
    private WaitingStatus status;

    public WaitingResponse(Waiting waiting) {
        this.id = waiting.getId();
        this.adult = waiting.getAdult();
        this.kid = waiting.getKid();
        this.total = waiting.getTotal();
        this.store_id = waiting.getStoreId();
        this.customer_id = waiting.getCustomerId();
        this.customer_name = waiting.getCustomerName();
        this.status = waiting.getStatus();
    }
}
