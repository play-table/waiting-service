package com.example.waiting.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Builder
@Entity
public class StoreWaitingInformation {
    @Id
    private UUID storeId;
    private String name;
    private String address;
}
