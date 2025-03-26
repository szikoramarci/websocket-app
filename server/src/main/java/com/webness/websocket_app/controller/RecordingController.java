package com.webness.websocket_app.controller;

import java.util.List;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import com.webness.websocket_app.dto.RecordingDto;
import com.webness.websocket_app.dto.RecordingUpdateRequest;
import com.webness.websocket_app.service.RecordingService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequiredArgsConstructor
public class RecordingController {

    private final RecordingService recordingService;

    @MessageMapping("/recording/list")
    @SendToUser("/topic/recordings")
    public List<RecordingDto> list() {
        log.info("Get all recording elements.");
        return recordingService.findAll();
    }

    @MessageMapping("/recording/get/{publicId}")
    @SendToUser("/topic/recording/single")
    public RecordingDto get(@DestinationVariable String publicId) {
        log.info("Get recording with publicId: " + publicId);
        return recordingService.findByPublicId(publicId);
    }

    @MessageMapping("/recording/update/{publicId}")
    @SendToUser("/topic/recording/updated")
    public RecordingDto update(
            @DestinationVariable String publicId,
            @Valid RecordingUpdateRequest request) {
        log.info("Updating recording with publicId: " + publicId);
        return recordingService.updateByPublicId(publicId, request);
    }

}
