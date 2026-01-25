package com.cpaums.controller;

import com.cpaums.dto.UpdateCheckResponseDto;
import com.cpaums.dto.UpdateLogRequestDto;
import com.cpaums.model.Platform;
import com.cpaums.service.UpdateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/update")
@RequiredArgsConstructor
public class UpdateController {
    
    private final UpdateService updateService;
    
    @GetMapping("/check")
    public UpdateCheckResponseDto checkUpdate(
            @RequestParam String userId,
            @RequestParam String current,
            @RequestParam Platform platform) {
        log.info("Update check for user: {}, version: {}, platform: {}", 
                 userId, current, platform);
        UpdateCheckResponseDto response = updateService.checkUpdate(userId, current, platform);
        log.info("Update check result: updateAvailable={}, latestVersion={}", 
                 response.isUpdateAvailable(), response.getLatestVersion());
        return response;
    }
    
    @PostMapping("/log")
    public String logUpdate(@Valid @RequestBody UpdateLogRequestDto request) {
        log.info("Logging update: user={}, from={}, to={}, platform={}, success={}",
                 request.getUserId(), request.getFromVersion(), 
                 request.getToVersion(), request.getPlatform(), request.isSuccess());
        String result = updateService.logUpdate(request);
        log.info("Update logged: {}", result);
        return result;
    }
}