package com.example.waiting.repository;

import com.example.waiting.domain.entity.Waiting;
import com.example.waiting.domain.response.WaitingResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WaitingRepository extends JpaRepository<Waiting, Long> {
    Optional<Waiting> findById(Long id);

    Optional<Waiting> findByStoreId(UUID storeId);

    @Query("select count(*) as waiting from Waiting ")
    List<Long> findByWaiting(Long id);

}