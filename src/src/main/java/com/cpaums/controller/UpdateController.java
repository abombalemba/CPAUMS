package com.cpaums.controller;

import com.cpaums.dto.UpdateCheckResponseDto;
import com.cpaums.dto.UpdateLogRequestDto;
import com.cpaums.model.Platform;
import com.cpaums.service.UpdateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
        return updateService.checkUpdate(userId, current, platform);
    }
    
    @PostMapping("/log")
    public String logUpdate(@Valid @RequestBody UpdateLogRequestDto request) {
        return updateService.logUpdate(request);
    }
}