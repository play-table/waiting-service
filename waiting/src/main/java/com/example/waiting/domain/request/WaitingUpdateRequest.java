package com.example.waiting.domain.request;

import com.example.waiting.domain.entity.WaitingStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WaitingUpdateRequest {
    private String status;
}
