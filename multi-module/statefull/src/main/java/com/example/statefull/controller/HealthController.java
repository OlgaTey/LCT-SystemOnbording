package com.example.statefull.controller;

import com.example.statefull.model.StatusResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ping")
public class HealthController {
    @GetMapping
    public ResponseEntity<StatusResponse> health() {
        StatusResponse response = new StatusResponse("UP");
        return ResponseEntity.ok(response);
    }
}
