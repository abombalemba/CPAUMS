package com.cpaums.controller;

import com.cpaums.dto.AppVersionResponseDto;
import com.cpaums.dto.CreateAppVersionRequest;
import com.cpaums.model.Platform;
import com.cpaums.service.AppVersionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/versions")
@RequiredArgsConstructor
public class AppVersionController {
    
    private final AppVersionService appVersionService;
    
    @PostMapping
    public ResponseEntity<AppVersionResponseDto> createVersion(
            @Valid @RequestBody CreateAppVersionRequest request) {
        AppVersionResponseDto response = appVersionService.createVersion(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping
    public List<AppVersionResponseDto> getAllVersions() {
        return appVersionService.getAllVersions();
    }
    
    @GetMapping("/latest")
    public ResponseEntity<AppVersionResponseDto> getLatestVersion(
            @RequestParam Platform platform) {
        AppVersionResponseDto response = appVersionService.getLatestVersion(platform);
        return response != null 
                ? ResponseEntity.ok(response) 
                : ResponseEntity.notFound().build();
    }
}