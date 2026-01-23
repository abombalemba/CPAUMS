package com.cpaums.service;

import com.cpaums.dto.AppVersionResponseDto;
import com.cpaums.dto.CreateAppVersionRequest;
import com.cpaums.exception.ResourceNotFoundException;
import com.cpaums.mapper.AppVersionMapper;
import com.cpaums.model.AppVersion;
import com.cpaums.model.Platform;
import com.cpaums.repository.AppVersionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppVersionService {
    
    private final AppVersionRepository repository;
    private final AppVersionMapper mapper;
    
    public AppVersionResponseDto createVersion(CreateAppVersionRequest request) {
        AppVersion version = mapper.toEntity(request);
        AppVersion saved = repository.save(version);
        return mapper.toDto(saved);
    }
    
    public List<AppVersionResponseDto> getAllVersions() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
    
    public AppVersionResponseDto getLatestVersion(Platform platform) {
        return repository
                .findFirstByPlatformAndIsActiveTrueOrderByReleaseDateDesc(platform)
                .map(mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "No active versions for platform: " + platform
                ));
    }
}