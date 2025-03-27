package com.webness.websocket_app.dto;

import com.webness.websocket_app.entity.RecordingStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecordingDto {

    private String publicId;
    private String title;
    private int duration;
    private RecordingStatus status;
    private String sedation;
    private String activation;
    private String medication;
    private UIConfig uiConfig;
}
