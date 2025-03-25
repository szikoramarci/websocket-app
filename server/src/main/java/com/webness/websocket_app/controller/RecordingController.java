package com.webness.websocket_app.controller;

import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

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

}
