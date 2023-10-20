package com.example.waiting.repository;

import com.example.waiting.domain.entity.WaitingHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface WaitingHistoryRepository extends JpaRepository<WaitingHistory, UUID> {
    Optional<WaitingHistory> findById(UUID id);

    Optional<WaitingHistory> findByStoreId(UUID storeId);

}