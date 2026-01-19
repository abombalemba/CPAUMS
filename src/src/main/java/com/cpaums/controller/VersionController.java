package com.cpaums.controller;

import com.cpaums.model.AppVersion;
import com.cpaums.model.Platform;
import com.cpaums.repository.AppVersionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/versions")
@RequiredArgsConstructor
public class VersionController {
    
    private final AppVersionRepository appVersionRepository;
    
    @GetMapping
    public List<AppVersion> getAllVersions() {
        return appVersionRepository.findAll();
    }
    
    @GetMapping("/latest")
    public ResponseEntity<AppVersion> getLatestVersion(@RequestParam Platform platform) {
        return appVersionRepository.findByPlatform(platform)
                .stream()
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}