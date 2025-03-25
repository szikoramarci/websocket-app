package com.webness.websocket_app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.webness.websocket_app.dto.RecordingDto;
import com.webness.websocket_app.mapper.RecordingMapper;
import com.webness.websocket_app.repository.RecordingRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecordingService {

    private final RecordingRepository recordingRepository;
    private final RecordingMapper recordingMapper;
    
    public List<RecordingDto> findAll() {
        return recordingRepository.findAll()
            .stream()
            .map(recordingMapper::toDto)
            .toList();
    }
}
