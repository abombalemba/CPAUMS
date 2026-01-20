package com.cpaums.controller;

import com.cpaums.dto.UpdateStatsDto;
import com.cpaums.model.AppVersion;
import com.cpaums.model.Platform;
import com.cpaums.repository.AppVersionRepository;
import com.cpaums.repository.UserDeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/stats")
@RequiredArgsConstructor
public class StatsController {
    
    private final UserDeviceRepository userDeviceRepository;
    private final AppVersionRepository appVersionRepository;
    
    @GetMapping("/updates")
    public UpdateStatsDto getUpdateStats() {
        UpdateStatsDto stats = new UpdateStatsDto();
        
        Map<Platform, Integer> usersCount = new HashMap<>();
        long latestVersionUsers = 0;
        String latestGlobalVersion = null;
        
        for (Platform platform : Platform.values()) {
            long count = userDeviceRepository.countByPlatform(platform);
            usersCount.put(platform, (int) count);
            
            AppVersion latestVersion = appVersionRepository
                .findFirstByPlatformAndIsActiveTrueOrderByReleaseDateDesc(platform)
                .orElse(null);
                
            if (latestVersion != null) {
                long countLatest = userDeviceRepository
                    .countByPlatformAndCurrentVersion(platform, latestVersion.getVersion());
                latestVersionUsers += countLatest;
                
                if (latestGlobalVersion == null) {
                    latestGlobalVersion = latestVersion.getVersion();
                } else {
                    AppVersion currentLatest = appVersionRepository
                        .findByVersion(latestGlobalVersion)
                        .orElse(null);
                    
                    if (currentLatest != null && 
                        latestVersion.getReleaseDate().isAfter(currentLatest.getReleaseDate())) {
                        latestGlobalVersion = latestVersion.getVersion();
                    }
                }
            }
        }
        
        stats.setVersion(latestGlobalVersion);
        stats.setUsersCount(usersCount);
        
        long totalUsers = userDeviceRepository.count();
        
        if (totalUsers > 0) {
            double updateRate = (double) latestVersionUsers / totalUsers * 100;
            stats.setGlobalUpdateRate(Math.round(updateRate * 100.0) / 100.0);
        } else {
            stats.setGlobalUpdateRate(0.0);
        }
        
        return stats;
    }
}