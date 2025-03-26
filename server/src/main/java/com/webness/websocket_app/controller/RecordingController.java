package com.webness.websocket_app.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.server.ResponseStatusException;

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
    @SendTo("/topic/recordings")
    public List<RecordingDto> list() {      
        log.info("Get all recording elements.");    
        return recordingService.findAll();
    }

    @MessageMapping("/recording/get/{publicId}")
    @SendTo("/topic/recording/single")
    public RecordingDto get(@DestinationVariable String publicId) {
        log.info("Get recording with publicId: " + publicId);
        return recordingService.findByPublicId(publicId);                
    }

    @MessageMapping("/recording/update/{publicId}")
    @SendToUser("/topic/recording/updated")
    public RecordingDto update(
        @DestinationVariable String publicId, 
        @Valid RecordingUpdateRequest request,
        Errors errors
    ) {
        log.info("Updating recording with publicId: " + publicId);
        
        if (errors.hasErrors()) {            
            String errorMessages = errors.getAllErrors().stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .collect(Collectors.joining("; "));

            log.warn("Validation failed: {}", errorMessages);
            throw new IllegalArgumentException("Invalid data: " + errorMessages);
        }

        return recordingService.updateByPublicId(publicId, request);
    }

}
