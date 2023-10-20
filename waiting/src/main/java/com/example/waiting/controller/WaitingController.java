package com.example.waiting.controller;

import com.example.waiting.config.MemberTokenInfo;
import com.example.waiting.domain.request.WaitingHistoryRequest;
import com.example.waiting.domain.request.WaitingRequest;
import com.example.waiting.domain.request.WaitingUpdateRequest;
import com.example.waiting.domain.response.WaitingHistoryResponse;
import com.example.waiting.domain.response.WaitingResponse;
import com.example.waiting.service.WaitingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/waiting")
public class WaitingController {
    private final WaitingService waitingService;

    @GetMapping
    public List<WaitingResponse> getAll() {
        return waitingService.getAll();
    }

    @GetMapping("/status")
    public List<WaitingResponse> getAllByStatus(@AuthenticationPrincipal MemberTokenInfo memberTokenInfo) {
        return waitingService.getAllByStatus(memberTokenInfo);
    }

    @PostMapping("/history/{storeId}")
    public void saveHistory(
            @AuthenticationPrincipal MemberTokenInfo memberTokenInfo,
            @PathVariable("storeId") String storeId,
            @RequestBody WaitingHistoryRequest waitingHistoryRequest
            ) {
       waitingService.saveHistory(storeId,memberTokenInfo,waitingHistoryRequest);
    }

    @PostMapping("{storeId}")
    public void save(
            @AuthenticationPrincipal MemberTokenInfo memberTokenInfo,
            @PathVariable("storeId") String storeId,
            @RequestBody WaitingRequest waitingRequest
    ) {
        waitingService.save(storeId, memberTokenInfo, waitingRequest);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        waitingService.delete(id,"RESTAURANT_CANCEL");
    }


    @GetMapping("/{storeId}")
    public List<Long> getByWaiting(@PathVariable("storeId") UUID storeId){
        return waitingService.findByWaiting(storeId);
    }


    @PutMapping("{storeId}/{status}")
    public WaitingResponse statusUpdate(
            @PathVariable("storeId") UUID storeId,
            @PathVariable("status") String status
            ) {
        return waitingService.statusUpdate(storeId,status);
    }
}
