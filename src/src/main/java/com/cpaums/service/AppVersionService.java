package com.cpaums.service;

import com.cpaums.dto.AppVersionResponseDto;
import com.cpaums.dto.CreateAppVersionRequest;
import com.cpaums.dto.UpdateAppVersionRequest;
import com.cpaums.exception.ResourceNotFoundException;
import com.cpaums.mapper.AppVersionMapper;
import com.cpaums.model.AppVersion;
import com.cpaums.model.Platform;
import com.cpaums.repository.AppVersionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppVersionService {
    
    private final AppVersionRepository repository;
    private final AppVersionMapper mapper;
    
    @Transactional
    public AppVersionResponseDto createVersion(CreateAppVersionRequest request) {
        AppVersion version = mapper.toEntity(request);
        AppVersion saved = repository.save(version);
        return mapper.toDto(saved);
    }
    
    @Transactional(readOnly = true)
    public List<AppVersionResponseDto> getAllVersions() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public AppVersionResponseDto getLatestVersion(Platform platform) {
        return repository
                .findFirstByPlatformAndIsActiveTrueOrderByReleaseDateDesc(platform)
                .map(mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "No active versions for platform: " + platform
                ));
    }

    @Transactional(readOnly = true)
    public AppVersionResponseDto getVersionById(Long id) {
        AppVersion version = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                "Version not found with id: " + id
            ));
        return mapper.toDto(version);
    }

    @Transactional
    public AppVersionResponseDto updateVersion(Long id, UpdateAppVersionRequest request) {
        AppVersion version = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Version not found with id: " + id
                ));
        
        if (request.getChangelog() != null) {
            version.setDescription(request.getChangelog());
        }
        if (request.getUpdateType() != null) {
            version.setUpdateType(request.getUpdateType());
        }
        if (request.getIsActive() != null) {
            version.setActive(request.getIsActive());
        }
        
        AppVersion saved = repository.save(version);
        return mapper.toDto(saved);
    }

    @Transactional
    public void deleteVersion(Long id) {
        AppVersion version = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Version not found with id: " + id
                ));
        version.setActive(false);
        repository.save(version);
    }
}