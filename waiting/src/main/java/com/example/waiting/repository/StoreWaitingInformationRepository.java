package com.example.waiting.repository;

import com.example.waiting.domain.entity.StoreWaitingInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StoreWaitingInformationRepository extends JpaRepository<StoreWaitingInformation, UUID> {
}
