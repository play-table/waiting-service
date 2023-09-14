package com.example.waiting.controller;

import com.example.waiting.domain.request.WaitingRequest;
import com.example.waiting.domain.request.WaitingUpdateRequest;
import com.example.waiting.domain.response.WaitingResponse;
import com.example.waiting.service.WaitingService;
import lombok.RequiredArgsConstructor;
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
    @PostMapping("{storeId}")
    public void save(@PathVariable("storeId") String storeId, @RequestBody WaitingRequest waitingRequest) {
        waitingService.save(storeId, waitingRequest);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        waitingService.delete(id);
    }

    @GetMapping("/id")
    public List<Long> findByWaiting(@RequestParam(name = "id") Long id) {
        return waitingService.findByWaiting(id);
    }

    @PutMapping("{storeId}/{status}")
    public WaitingResponse statusUpdate(
            @PathVariable("storeId") String storeId,
            @PathVariable("status") String status
            ) {
        return waitingService.statusUpdate(storeId,status);
    }
}
