package com.example.waiting.service;

import com.example.waiting.config.MemberTokenInfo;
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

    public void save(String storeId, MemberTokenInfo memberTokenInfo, WaitingRequest waitingRequest) {
        waitingRepository.save(waitingRequest.toEntity(storeId, memberTokenInfo.getId(), memberTokenInfo.getRealName()));
    }

    public WaitingResponse statusUpdate(UUID storeId, String status) {
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

    private Waiting findByStoreId(UUID storeId) {
        return waitingRepository.findByStoreId(storeId).orElseThrow(()->new NoSuchElementException("storeId 못찾음"));
    }
    public List<Long> findByWaiting(UUID storeId) {
        return waitingRepository.findByWaiting(storeId);
    }

    public Long findCountByWaitingStatusLikeEntrance() {
        return waitingRepository.findCountByWaitingStatusLikeEntrance();}

    public Long findCountByWaitingStatusLikeCancel() {
        return waitingRepository.findCountByWaitingStatusLikeCancel();
    }

    public List<WaitingResponse> getAllByStatus(MemberTokenInfo memberTokenInfo) {
        return waitingRepository.findAllByStatus(memberTokenInfo.getId()).stream().map(WaitingResponse::new).toList();
    }
}