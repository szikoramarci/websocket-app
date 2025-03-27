package com.webness.websocket_app.dto;

import com.webness.websocket_app.entity.RecordingStatus;
import com.webness.websocket_app.policy.RecordingPolicy;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UIConfig {
    private String severity;
    private String label;
    private boolean editable;

    public static UIConfig fromStatus(RecordingStatus status) {
        String severity = switch (status) {
            case RECORDED -> "danger";
            case SCHEDULED -> "success";
            default -> "warn";
        };

        boolean editable = RecordingPolicy.isEditable(status);

        return new UIConfig(severity, status.name(), editable);
    }
}