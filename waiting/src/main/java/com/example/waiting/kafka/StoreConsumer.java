package com.example.waiting.kafka;

import com.example.waiting.domain.kafka.StoreUpdateKafkaData;

import com.example.waiting.service.WaitingService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StoreConsumer {
    private final WaitingService waitingService;

    @KafkaListener(topics = TopicConfig.STORE)
    public void listen(StoreUpdateKafkaData data) {
        waitingService.update(data);
    }


}
