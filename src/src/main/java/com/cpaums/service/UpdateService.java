package com.cpaums.service;

import com.cpaums.dto.UpdateCheckResponseDto;
import com.cpaums.dto.UpdateLogRequestDto;
import com.cpaums.model.AppVersion;
import com.cpaums.model.Platform;
import com.cpaums.model.UserDevice;
import com.cpaums.repository.AppVersionRepository;
import com.cpaums.repository.UserDeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateService {
    
    private final AppVersionRepository appVersionRepository;
    private final UserDeviceRepository userDeviceRepository;
    
    public UpdateCheckResponseDto checkUpdate(String userId, String currentVersion, Platform platform) {
        updateUserDevice(userId, currentVersion, platform);
        
        Optional<AppVersion> latestOpt = appVersionRepository
                .findFirstByPlatformAndIsActiveTrueOrderByReleaseDateDesc(platform);
        
        UpdateCheckResponseDto response = new UpdateCheckResponseDto();
        
        if (latestOpt.isEmpty()) {
            response.setUpdateAvailable(false);
            response.setMessage("No active versions for platform: " + platform);
            return response;
        }
        
        AppVersion latest = latestOpt.get();
        
        if (latest.getVersion().equals(currentVersion)) {
            response.setUpdateAvailable(false);
            response.setMessage("You have the latest version: " + currentVersion);
            return response;
        }
        
        response.setUpdateAvailable(true);
        response.setLatestVersion(latest.getVersion());
        response.setUpdateType(latest.getUpdateType());
        response.setMessage(String.format(
                "Update available! New version: %s (%s)",
                latest.getVersion(),
                latest.getUpdateType()
        ));
        
        return response;
    }
    
    public String logUpdate(UpdateLogRequestDto request) {
        String logMessage = String.format(
                "User %s updated from %s to %s on %s. Success: %s",
                request.getUserId(),
                request.getFromVersion(),
                request.getToVersion(),
                request.getPlatform(),
                request.isSuccess()
        );
        
        System.out.println("Update log: " + logMessage);
        
        return "Update logged: " + logMessage;
    }
    
    private void updateUserDevice(String userId, String currentVersion, Platform platform) {
        List<UserDevice> devices = userDeviceRepository.findByUserId(userId);
        UserDevice device = devices.isEmpty() ? new UserDevice() : devices.get(0);
        
        device.setUserId(userId);
        device.setPlatform(platform);
        device.setCurrentVersion(currentVersion);
        device.setLastSeen(LocalDateTime.now());
        userDeviceRepository.save(device);
    }
}