package com.cpaums.controller;

import com.cpaums.dto.CreateUserDeviceRequest;
import com.cpaums.dto.UserDeviceResponseDto;
import com.cpaums.service.UserDeviceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
@RequiredArgsConstructor
public class UserDeviceController {
    
    private final UserDeviceService service;
    
    @PostMapping
    public ResponseEntity<UserDeviceResponseDto> createDevice(
            @Valid @RequestBody CreateUserDeviceRequest request) {
        UserDeviceResponseDto response = service.createDevice(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping
    public List<UserDeviceResponseDto> getAllDevices() {
        return service.getAllDevices();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserDeviceResponseDto> getDeviceById(@PathVariable Long id) {
        UserDeviceResponseDto response = service.getDeviceById(id);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/user/{userId}")
    public List<UserDeviceResponseDto> getDevicesByUserId(@PathVariable String userId) {
        return service.getDevicesByUserId(userId);
    }
    
    @PutMapping("/{id}/version")
    public ResponseEntity<UserDeviceResponseDto> updateDeviceVersion(
            @PathVariable Long id,
            @RequestParam String version) {
        UserDeviceResponseDto response = service.updateDeviceVersion(id, version);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable Long id) {
        service.deleteDevice(id);
        return ResponseEntity.noContent().build();
    }
}