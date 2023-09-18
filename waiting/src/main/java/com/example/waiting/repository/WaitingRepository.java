package com.example.waiting.repository;

import com.example.waiting.domain.entity.Waiting;
import com.example.waiting.domain.response.WaitingResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WaitingRepository extends JpaRepository<Waiting, Long> {
    Optional<Waiting> findById(Long id);

    Optional<Waiting> findByStoreId(UUID storeId);

    @Query("select count(*) as waiting from Waiting ")
    List<Long> findByWaiting(UUID storeId);

    @Query
    ("select count(*) as entrance_count from Waiting as w where w.status = 'ENTRANCE'")
    Long findCountByWaitingStatusLikeEntrance();

    @Query
    ("select count(*) as cancel_count from Waiting as w where w.status = 'CUSTOMER_CANCEL' or w.status = 'RESTAURANT_CANCEL'")
    Long findCountByWaitingStatusLikeCancel();

    @Query
    ("select w from Waiting as w where w.customerId=:customerId and w.status = 'ENTRANCE' or w.status = 'CUSTOMER_CANCEL' or w.status = 'RESTAURANT_CANCEL'")
    List<Waiting> findAllByStatus(@Param("customerId") UUID customerId);
}