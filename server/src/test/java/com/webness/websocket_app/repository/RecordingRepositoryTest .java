package com.webness.websocket_app.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.webness.websocket_app.entity.Recording;
import com.webness.websocket_app.entity.RecordingStatus;

@DataJpaTest
class RecordingRepositoryTest {

    @Autowired
    private RecordingRepository recordingRepository;

    @Test
    void shouldFindRecordingByPublicId() {
        // given
        Recording recording = new Recording();
        recording.setPublicId("abc123");
        recording.setTitle("TEST");
        recording.setDuration(2000);
        recording.setActivation("ACT");
        recording.setMedication("MED");
        recording.setSedation("SED");
        recording.setStatus(RecordingStatus.RECORDED);
        recordingRepository.save(recording);

        // when
        Optional<Recording> result = recordingRepository.findByPublicId("abc123");

        // then
        assertTrue(result.isPresent());
        assertEquals("TEST", result.get().getTitle());
    }

    @Test
    void shouldReturnEmptyWhenPublicIdNotFound() {
        // when
        Optional<Recording> result = recordingRepository.findByPublicId("nonexistent-id");

        // then
        assertTrue(result.isEmpty());
    }
}