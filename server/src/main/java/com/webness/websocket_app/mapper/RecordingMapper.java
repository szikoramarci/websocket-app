package com.webness.websocket_app.mapper;

import org.springframework.stereotype.Component;

import com.webness.websocket_app.dto.RecordingDto;
import com.webness.websocket_app.entity.Recording;

@Component
public class RecordingMapper {

    public Recording toEntity(RecordingDto dto) {
        return Recording.builder()
                .publicId(dto.getPublicId())
                .title(dto.getTitle())
                .duration(dto.getDuration())
                .status(dto.getStatus())
                .sedation(dto.getSedation())
                .activation(dto.getActivation())
                .medication(dto.getMedication())
                .build();   
    }

    public RecordingDto toDto(Recording entity) {
        return RecordingDto.builder()
                .publicId(entity.getPublicId())
                .title(entity.getTitle())
                .duration(entity.getDuration())
                .status(entity.getStatus())
                .sedation(entity.getSedation())
                .activation(entity.getActivation())
                .medication(entity.getMedication())
                .build();
    }   

}
