package com.cpaums.controller;

import com.cpaums.dto.AppVersionResponseDto;
import com.cpaums.dto.CreateAppVersionRequest;
import com.cpaums.mapper.AppVersionMapper;
import com.cpaums.model.AppVersion;
import com.cpaums.model.Platform;
import com.cpaums.repository.AppVersionRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/versions")
@RequiredArgsConstructor
public class AppVersionController {
    
    private final AppVersionRepository repository;
    private final AppVersionMapper mapper;
    
    @PostMapping
    public ResponseEntity<AppVersionResponseDto> createVersion(
            @Valid @RequestBody CreateAppVersionRequest request) {
        
        AppVersion version = mapper.toEntity(request);
        AppVersion saved = repository.save(version);
        AppVersionResponseDto response = mapper.toDto(saved);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping
    public List<AppVersionResponseDto> getAllVersions() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/latest")
    public ResponseEntity<AppVersionResponseDto> getLatestVersion(
            @RequestParam Platform platform) {
        
        return repository
                .findFirstByPlatformAndIsActiveTrueOrderByReleaseDateDesc(platform)
                .map(mapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}