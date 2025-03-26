package com.webness.websocket_app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.server.ResponseStatusException;

import com.webness.websocket_app.dto.RecordingDto;
import com.webness.websocket_app.service.RecordingService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RecordingController {

    private final RecordingService recordingService;

    @MessageMapping("/recording/list")
    @SendTo("/topic/recordings")
    public List<RecordingDto> list() {      
        return recordingService.findAll();
    }

    @MessageMapping("/recording/get/{publicId}")
    @SendTo("/topic/recording/single")
    public RecordingDto get(@DestinationVariable String publicId) {
        return recordingService.findByPublicId(publicId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
