package com.lifeguardian.sos_backend.controller;

import com.lifeguardian.sos_backend.dto.SosRequest;
import com.lifeguardian.sos_backend.service.SosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sos")
@RequiredArgsConstructor
public class SosController {

    private final SosService sosService;

    @PostMapping
    public ResponseEntity<String> triggerSos(
            @RequestBody SosRequest request) {

        sosService.sendSos(request);
        return ResponseEntity.ok("SOS sent successfully");
    }
}
