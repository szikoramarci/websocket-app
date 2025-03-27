package com.webness.websocket_app.policy;

import com.webness.websocket_app.entity.RecordingStatus;

public class RecordingPolicy {

    public static boolean isEditable(RecordingStatus status) {
        return switch (status) {
            case RECORDED -> true;
            default -> false;
        };
    }

}
