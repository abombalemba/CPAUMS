package com.cpaums.mapper;

import com.cpaums.dto.AppVersionResponseDto;
import com.cpaums.dto.CreateAppVersionRequest;
import com.cpaums.model.AppVersion;
import org.springframework.stereotype.Component;

@Component
public class AppVersionMapper {
    
    public AppVersionResponseDto toDto(AppVersion version) {
        if (version == null) return null;
        
        AppVersionResponseDto dto = new AppVersionResponseDto();
        dto.setVersion(version.getVersion());
        dto.setPlatform(version.getPlatform().name());
        dto.setReleaseDate(version.getReleaseDate());
        dto.setChangelog(version.getDescription());
        dto.setUpdateType(version.getUpdateType());
        return dto;
    }
    
    public AppVersion toEntity(CreateAppVersionRequest request) {
        if (request == null) return null;
        
        AppVersion version = new AppVersion();
        version.setVersion(request.getVersion());
        version.setPlatform(request.getPlatform());
        version.setReleaseDate(request.getReleaseDate());
        version.setDescription(request.getChangelog());
        version.setUpdateType(request.getUpdateType());
        version.setActive(true);
        return version;
    }
}