package com.webness.websocket_app.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.webness.websocket_app.dto.RecordingDto;
import com.webness.websocket_app.dto.RecordingUpdateRequest;
import com.webness.websocket_app.entity.Recording;
import com.webness.websocket_app.entity.RecordingStatus;
import com.webness.websocket_app.mapper.RecordingMapper;
import com.webness.websocket_app.repository.RecordingRepository;
import com.webness.websocket_app.service.RecordingService;

@ExtendWith(MockitoExtension.class)
class RecordingServiceTest {

    @Mock
    RecordingRepository recordingRepository;

    @Mock
    RecordingMapper recordingMapper;

    @InjectMocks
    RecordingService recordingService;

    @Test
    void findAll_shouldReturnMappedDtos() {
        // given
        Recording entity = new Recording();
        RecordingDto dto = new RecordingDto();
        when(recordingRepository.findAll()).thenReturn(List.of(entity));
        when(recordingMapper.toDto(entity)).thenReturn(dto);

        // when
        List<RecordingDto> result = recordingService.findAll();

        // then
        assertEquals(1, result.size());
        assertSame(dto, result.get(0));
    }

    @Test
    void updateByPublicId_shouldUpdateAndReturnDto_whenRecordingIsEditable() {
        // given
        String publicId = "abc123";
        Recording entity = new Recording();
        entity.setStatus(RecordingStatus.RECORDED);

        RecordingDto dto = new RecordingDto();
        RecordingUpdateRequest req = new RecordingUpdateRequest();
        req.setActivation("TEST_ACT");
        req.setSedation("TEST_SED");
        req.setMedication("TEST_MED");

        when(recordingRepository.findByPublicId(publicId)).thenReturn(Optional.of(entity));
        when(recordingRepository.save(entity)).thenReturn(entity);
        when(recordingMapper.toDto(entity)).thenReturn(dto);

        // when
        RecordingDto result = recordingService.updateByPublicId(publicId, req);

        // then
        assertEquals(dto, result);
        assertEquals("TEST_ACT", entity.getActivation());
        assertEquals("TEST_SED", entity.getSedation());
        assertEquals("TEST_MED", entity.getMedication());
    }

    @Test
    void updateByPublicId_shouldThrowException_whenRecordingIsNotEditable() {
        // given
        String publicId = "not-editable-id";
        Recording entity = new Recording();
        entity.setStatus(RecordingStatus.SCHEDULED);

        RecordingUpdateRequest req = new RecordingUpdateRequest();
        req.setActivation("X");
        req.setSedation("Y");
        req.setMedication("Z");

        when(recordingRepository.findByPublicId(publicId)).thenReturn(Optional.of(entity));

        // when + then
        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                () -> recordingService.updateByPublicId(publicId, req));

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, ex.getStatusCode());
        assertEquals("This recording is not editable.", ex.getReason());
    }
}