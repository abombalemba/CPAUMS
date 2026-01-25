package com.cpaums.controller;

import com.cpaums.dto.AppVersionResponseDto;
import com.cpaums.dto.CreateAppVersionRequest;
import com.cpaums.dto.UpdateAppVersionRequest;
import com.cpaums.model.Platform;
import com.cpaums.service.AppVersionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/versions")
@RequiredArgsConstructor
public class AppVersionController {
    
    private final AppVersionService appVersionService;
    
    @PostMapping
    public ResponseEntity<AppVersionResponseDto> createVersion(
            @Valid @RequestBody CreateAppVersionRequest request) {
        log.info("Creating new app version: {}", request);
        AppVersionResponseDto response = appVersionService.createVersion(request);
        log.info("App version created successfully: {}", response);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping
    public List<AppVersionResponseDto> getAllVersions() {
        log.debug("Getting all app versions");
        return appVersionService.getAllVersions();
    }
    
    @GetMapping("/latest")
    public ResponseEntity<AppVersionResponseDto> getLatestVersion(
            @RequestParam Platform platform) {
        log.debug("Getting latest version for platform: {}", platform);
        AppVersionResponseDto response = appVersionService.getLatestVersion(platform);
        if (response != null) {
            log.debug("Found latest version: {}", response);
            return ResponseEntity.ok(response);
        } else {
            log.warn("No latest version found for platform: {}", platform);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppVersionResponseDto> getVersionById(@PathVariable Long id) {
        log.debug("Getting app version by id: {}", id);
        AppVersionResponseDto response = appVersionService.getVersionById(id);
        log.debug("Found app version: {}", response);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppVersionResponseDto> updateVersion(
            @PathVariable Long id,
            @Valid @RequestBody UpdateAppVersionRequest request) {
        log.info("Updating app version id {} with data: {}", id, request);
        AppVersionResponseDto response = appVersionService.updateVersion(id, request);
        log.info("App version updated successfully: {}", response);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVersion(@PathVariable Long id) {
        log.info("Deleting app version id: {}", id);
        appVersionService.deleteVersion(id);
        log.info("App version deleted successfully: {}", id);
        return ResponseEntity.noContent().build();
    }
}