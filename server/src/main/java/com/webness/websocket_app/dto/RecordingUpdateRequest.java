package com.webness.websocket_app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RecordingUpdateRequest {

    @NotBlank(message = "The sedation should be filled.")
    @Size(max = 1000, message = "The sedation should be maximum 1000 charachters long.")
    String sedation;

    @NotBlank(message = "The sedation should be filled.")
    @Size(max = 1000, message = "The sedation should be maximum 1000 charachters long.")
    String activation;

    @NotBlank(message = "The sedation should be filled.")
    @Size(max = 1000, message = "The sedation should be maximum 1000 charachters long.")
    String medication;
}
