package com.cpaums.controller;

import com.cpaums.model.AppVersion;
import com.cpaums.model.Platform;
import com.cpaums.model.UserDevice;
import com.cpaums.repository.AppVersionRepository;
import com.cpaums.repository.UserDeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/update")
@RequiredArgsConstructor
public class UpdateController {
    
    private final AppVersionRepository appVersionRepository;
    private final UserDeviceRepository userDeviceRepository;
    
    @GetMapping("/check")
    public String checkUpdate(
            @RequestParam String userId,
            @RequestParam String current,
            @RequestParam Platform platform) {
        
        List<UserDevice> devices = userDeviceRepository.findByUserId(userId);
        UserDevice device;
        
        if (devices.isEmpty()) {
            device = new UserDevice();
        } else {
            device = devices.get(0);
        }
        
        device.setUserId(userId);
        device.setPlatform(platform);
        device.setCurrentVersion(current);
        device.setLastSeen(LocalDateTime.now());
        userDeviceRepository.save(device);
        
        Optional<AppVersion> latestOpt = appVersionRepository.findFirstByPlatformAndIsActiveTrueOrderByReleaseDateDesc(platform);
        
        if (latestOpt.isEmpty()) {
            return "No active versions for platform: " + platform;
        }
        
        AppVersion latest = latestOpt.get();
        
        if (latest.getVersion().equals(current)) {
            return "You have the latest version: " + current;
        }
        
        return String.format(
            "Update available! New version: %s (%s). Your version: %s",
            latest.getVersion(),
            latest.getUpdateType(),
            current
        );
    }
    
    @PostMapping("/log")
    public String logUpdate(
            @RequestParam String userId,
            @RequestParam String fromVersion,
            @RequestParam String toVersion,
            @RequestParam Platform platform,
            @RequestParam boolean success) {
        
        String logMessage = String.format(
            "User %s updated from %s to %s on %s. Success: %s",
            userId, fromVersion, toVersion, platform, success
        );
        
        System.out.println("Update log: " + logMessage);
        
        return "Update logged: " + logMessage;
    }
}