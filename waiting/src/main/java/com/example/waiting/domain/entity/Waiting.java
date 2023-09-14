package com.example.waiting.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Table(name = "waiting")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Waiting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer adult;
    private Integer kid;
    private Integer total;
    private UUID storeId;
    private UUID customerId;
    private String customerName;
    @Enumerated(EnumType.STRING)
    private WaitingStatus status;

    public void statusUpdate(WaitingStatus status) {
        this.status = status;
    }

}
