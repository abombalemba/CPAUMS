package com.cpaums.service;

import com.cpaums.dto.UpdateCheckResponseDto;
import com.cpaums.dto.UpdateLogRequestDto;
import com.cpaums.model.AppVersion;
import com.cpaums.model.Platform;
import com.cpaums.model.UpdateLog;
import com.cpaums.model.UserDevice;
import com.cpaums.repository.AppVersionRepository;
import com.cpaums.repository.UpdateLogRepository;
import com.cpaums.repository.UserDeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateService {
    
    private final AppVersionRepository appVersionRepository;
    private final UserDeviceRepository userDeviceRepository;
    private final UpdateLogRepository updateLogRepository;
    
    @Transactional
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
    
    @Transactional
    public String logUpdate(UpdateLogRequestDto request) {
        UpdateLog log = new UpdateLog();
        log.setUserId(request.getUserId());
        log.setFromVersion(request.getFromVersion());
        log.setToVersion(request.getToVersion());
        log.setPlatform(request.getPlatform());
        log.setSuccess(request.isSuccess());
        log.setTimestamp(java.time.LocalDateTime.now());
        
        updateLogRepository.save(log);
        
        // Return a generic message that does not include user-provided input
        return "Update logged";
    }
    
    @Transactional
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