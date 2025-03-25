package com.webness.websocket_app.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.webness.websocket_app.entity.Recording;
import com.webness.websocket_app.entity.RecordingStatus;
import com.webness.websocket_app.repository.RecordingRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RecordingRepository recordingRepository;

    @Override
    public void run(String... args) throws Exception {

        recordingRepository.save(
            Recording.builder()
                .title("TEST RECORD 1")
                .duration(400)
                .medication("MED 1")
                .sedation("SED 1")                
                .activation("ACT 1")
                .status(RecordingStatus.RECORDED)
                .build()
        );

        recordingRepository.save(
            Recording.builder()
                .title("TEST RECORD 2")
                .duration(200)
                .medication("MED 2")
                .sedation("SED 2")                
                .activation("ACT 2")
                .status(RecordingStatus.SCHEDULED)
                .build()
        );

        recordingRepository.save(
            Recording.builder()
                .title("TEST RECORD 3")
                .duration(1000)
                .medication("MED 3")
                .sedation("SED 3")                
                .activation("ACT 3")
                .status(RecordingStatus.REPORTED)
                .build()
        );

        System.out.println("Test data has been added.");
    }
}
