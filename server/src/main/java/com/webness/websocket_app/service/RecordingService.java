package com.webness.websocket_app.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.webness.websocket_app.dto.RecordingDto;
import com.webness.websocket_app.dto.RecordingUpdateRequest;
import com.webness.websocket_app.entity.Recording;
import com.webness.websocket_app.mapper.RecordingMapper;
import com.webness.websocket_app.repository.RecordingRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class RecordingService {

    private final RecordingRepository recordingRepository;
    private final RecordingMapper recordingMapper;

    @Transactional(readOnly = true)
    public List<RecordingDto> findAll() {
        return recordingRepository.findAll()
                .stream()
                .map(recordingMapper::toDto)
                .toList();
    }

    @Transactional()
    public RecordingDto updateByPublicId(String publicId, RecordingUpdateRequest request) {
        log.debug("Update details - sedation: " + request.getSedation()
                + ", activation: " + request.getActivation()
                + ", medication: " + request.getMedication());

        Recording recording = recordingRepository.findByPublicId(publicId).orElseThrow(() -> {
            log.warn("No recording found with publicId: " + publicId);
            return new ResponseStatusException(HttpStatus.NOT_FOUND);
        });

        recording.setSedation(request.getSedation());
        recording.setActivation(request.getActivation());
        recording.setMedication(request.getMedication());

        return recordingMapper.toDto(recordingRepository.save(recording));
    }

}
