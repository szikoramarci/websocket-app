package com.webness.websocket_app.contoller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.webness.websocket_app.controller.RecordingController;
import com.webness.websocket_app.dto.RecordingDto;
import com.webness.websocket_app.dto.RecordingUpdateRequest;
import com.webness.websocket_app.service.RecordingService;

@ExtendWith(MockitoExtension.class)
class RecordingControllerTest {

    @Mock
    RecordingService recordingService;

    @InjectMocks
    RecordingController controller;

    @Test
    void list_shouldReturnAllRecordings() {
        List<RecordingDto> expected = List.of(new RecordingDto());
        when(recordingService.findAll()).thenReturn(expected);

        List<RecordingDto> result = controller.list();

        assertEquals(expected, result);
    }

    @Test
    void list_shouldReturnEmptyList_whenNoRecordingsExist() {
        when(recordingService.findAll()).thenReturn(List.of());

        List<RecordingDto> result = controller.list();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void get_shouldReturnRecordingDto_whenFound() {
        // given
        String publicId = "abc123";
        RecordingDto dto = new RecordingDto();
        when(recordingService.findByPublicId(publicId)).thenReturn(dto);

        // when
        RecordingDto result = controller.get(publicId);

        // then
        assertSame(dto, result);
        verify(recordingService).findByPublicId(publicId);
    }

    @Test
    void update_shouldCallServiceAndReturnDto() {
        // given
        String publicId = "abc123";
        RecordingUpdateRequest req = new RecordingUpdateRequest();
        req.setActivation("TEST_ACT");
        req.setSedation("TEST_SED");
        req.setMedication("TEST_MED");

        RecordingDto expectedDto = new RecordingDto();
        when(recordingService.updateByPublicId(publicId, req)).thenReturn(expectedDto);

        // when
        RecordingDto result = controller.update(publicId, req);

        // then
        assertSame(expectedDto, result);
        verify(recordingService).updateByPublicId(publicId, req);
    }
}