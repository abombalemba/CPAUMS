package com.cpaums.mapper;

import com.cpaums.dto.CreateUserDeviceRequest;
import com.cpaums.dto.UserDeviceResponseDto;
import com.cpaums.model.UserDevice;
import org.springframework.stereotype.Component;

@Component
public class UserDeviceMapper {
    
    public UserDeviceResponseDto toDto(UserDevice device) {
        UserDeviceResponseDto dto = new UserDeviceResponseDto();
        dto.setId(device.getId());
        dto.setUserId(device.getUserId());
        dto.setPlatform(device.getPlatform());
        dto.setCurrentVersion(device.getCurrentVersion());
        dto.setLastSeen(device.getLastSeen());
        return dto;
    }
    
    public UserDevice toEntity(CreateUserDeviceRequest request) {
        UserDevice device = new UserDevice();
        device.setUserId(request.getUserId());
        device.setPlatform(request.getPlatform());
        device.setCurrentVersion(request.getCurrentVersion());
        device.setLastSeen(java.time.LocalDateTime.now());
        return device;
    }
}
