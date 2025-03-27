package com.webness.websocket_app.policies;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.webness.websocket_app.entity.RecordingStatus;
import com.webness.websocket_app.policy.RecordingPolicy;

class RecordingPolicyTest {

    @Test
    void isEditable_shouldReturnTrue_whenStatusIsRecorded() {
        assertTrue(RecordingPolicy.isEditable(RecordingStatus.RECORDED));
    }

    @Test
    void isEditable_shouldReturnFalse_whenStatusIsScheduled() {
        assertFalse(RecordingPolicy.isEditable(RecordingStatus.SCHEDULED));
    }

    @Test
    void isEditable_shouldReturnFalse_whenStatusIsRecording() {
        assertFalse(RecordingPolicy.isEditable(RecordingStatus.REPORTED));
    }
}