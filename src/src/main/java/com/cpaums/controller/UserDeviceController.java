package com.cpaums.controller;

import com.cpaums.dto.CreateUserDeviceRequest;
import com.cpaums.dto.UserDeviceResponseDto;
import com.cpaums.service.UserDeviceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/devices")
@RequiredArgsConstructor
public class UserDeviceController {
    
    private final UserDeviceService service;
    
    @PostMapping
    public ResponseEntity<UserDeviceResponseDto> createDevice(
            @Valid @RequestBody CreateUserDeviceRequest request) {
        log.info("Creating device for user: {}", request.getUserId());
        UserDeviceResponseDto response = service.createDevice(request);
        log.info("Device created successfully: {}", response);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping
    public List<UserDeviceResponseDto> getAllDevices() {
        log.debug("Getting all devices");
        return service.getAllDevices();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserDeviceResponseDto> getDeviceById(@PathVariable Long id) {
        log.debug("Getting device by id: {}", id);
        UserDeviceResponseDto response = service.getDeviceById(id);
        log.debug("Found device: {}", response);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/user/{userId}")
    public List<UserDeviceResponseDto> getDevicesByUserId(@PathVariable String userId) {
        log.debug("Getting devices for user: {}", userId);
        return service.getDevicesByUserId(userId);
    }
    
    @PutMapping("/{id}/version")
    public ResponseEntity<UserDeviceResponseDto> updateDeviceVersion(
            @PathVariable Long id,
            @RequestParam String version) {
        log.info("Updating device {} version to: {}", id, version);
        UserDeviceResponseDto response = service.updateDeviceVersion(id, version);
        log.info("Device version updated successfully: {}", response);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable Long id) {
        log.info("Deleting device: {}", id);
        service.deleteDevice(id);
        log.info("Device deleted successfully: {}", id);
        return ResponseEntity.noContent().build();
    }
}