package com.webness.websocket_app.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class RecordingController {

    @MessageMapping("/recording/save")
    @SendTo("/topic/recordings")
    public List<String> handleRecording() {      
        return Arrays.asList("Recording saved","TESZT","Aaa");
    }

}
