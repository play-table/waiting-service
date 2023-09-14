package com.example.waiting.service;

import com.example.waiting.domain.entity.Waiting;
import com.example.waiting.domain.entity.WaitingStatus;
import com.example.waiting.domain.request.WaitingRequest;
import com.example.waiting.domain.request.WaitingUpdateRequest;
import com.example.waiting.domain.response.WaitingResponse;
import com.example.waiting.repository.WaitingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class WaitingService {
    private final WaitingRepository waitingRepository;

    public List<WaitingResponse> getAll() {
        return waitingRepository.findAll().stream().map(WaitingResponse::new).toList();
    }

    public void save(String storeId,WaitingRequest waitingRequest) {
        waitingRepository.save(waitingRequest.toEntity(storeId));
    }

    public WaitingResponse statusUpdate(String storeId, String status) {
        Waiting waiting = findByStoreId(storeId);
        waiting.statusUpdate(WaitingStatus.valueOf(status.toUpperCase()));
        return new WaitingResponse(waiting);
    }

    public void delete(Long id) {
        waitingRepository.deleteById(id);
    }

    private Waiting findById(Long id) {
        return waitingRepository.findById(id).orElseThrow(()->new RuntimeException());
    }

    private Waiting findByStoreId(String storeId) {
        return waitingRepository.findByStoreId(UUID.fromString(storeId)).orElseThrow(()->new NoSuchElementException("storeId 못찾음"));
    }
    public List<Long> findByWaiting(Long id) {
        return waitingRepository.findByWaiting(id);
    }
}