package com.example.waiting.service;

import com.example.waiting.config.MemberTokenInfo;
import com.example.waiting.domain.entity.StoreWaitingInformation;
import com.example.waiting.domain.entity.Waiting;
import com.example.waiting.domain.entity.WaitingHistory;
import com.example.waiting.domain.entity.WaitingStatus;
import com.example.waiting.domain.kafka.StoreUpdateKafkaData;
import com.example.waiting.domain.request.WaitingHistoryRequest;
import com.example.waiting.domain.request.WaitingRequest;
import com.example.waiting.domain.request.WaitingUpdateRequest;
import com.example.waiting.domain.response.WaitingHistoryResponse;
import com.example.waiting.domain.response.WaitingResponse;
import com.example.waiting.repository.StoreWaitingInformationRepository;
import com.example.waiting.repository.WaitingHistoryRepository;
import com.example.waiting.repository.WaitingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class WaitingService {
    private final WaitingRepository waitingRepository;
    private final WaitingHistoryRepository waitingHistoryRepository;
    private final StoreWaitingInformationRepository storeWaitingInformationRepository;

    public List<WaitingResponse> getAll() {
        return waitingRepository.findAll().stream().map(WaitingResponse::new).toList();
    }

    public void save(String storeId, MemberTokenInfo memberTokenInfo, WaitingRequest waitingRequest) {
        waitingRepository.save(waitingRequest
                .toEntity(storeId, memberTokenInfo.getId(), memberTokenInfo.getRealName()));
    }

    public void update(StoreUpdateKafkaData data) {
        Optional<StoreWaitingInformation> byId = storeWaitingInformationRepository.findById(data.storeId());
        if (byId.isEmpty()) {
            return;
        }
        StoreWaitingInformation information = byId.get();
        information.setName(data.name());
        information.setAddress(data.address());
    }

    public WaitingResponse statusUpdate(UUID storeId, String status) {
        Waiting waiting = findByStoreId(storeId);
        waiting.statusUpdate(WaitingStatus.valueOf(status.toUpperCase()));
        return new WaitingResponse(waiting);
    }

    public void delete(Long id, String status) {
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

    public void saveHistory(String storeId, MemberTokenInfo memberTokenInfo, WaitingHistoryRequest waitingHistoryRequest) {
       waitingHistoryRepository.save(waitingHistoryRequest
                .toEntity(storeId, memberTokenInfo.getId(), memberTokenInfo.getRealName()));
       statusUpdate(UUID.fromString(storeId),"ENTRANCE");
    }
}