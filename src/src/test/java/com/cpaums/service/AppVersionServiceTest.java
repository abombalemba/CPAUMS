package com.cpaums.service;

import com.cpaums.dto.AppVersionResponseDto;
import com.cpaums.dto.CreateAppVersionRequest;
import com.cpaums.dto.UpdateAppVersionRequest;
import com.cpaums.exception.ResourceNotFoundException;
import com.cpaums.mapper.AppVersionMapper;
import com.cpaums.model.AppVersion;
import com.cpaums.model.Platform;
import com.cpaums.model.UpdateType;
import com.cpaums.repository.AppVersionRepository;
import com.cpaums.service.AppVersionService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppVersionServiceTest {
    
    @Mock
    private AppVersionRepository repository;
    
    @Mock
    private AppVersionMapper mapper;
    
    @InjectMocks
    private AppVersionService appVersionService;
    
    private AppVersion appVersion;
    private AppVersionResponseDto responseDto;
    private CreateAppVersionRequest createRequest;
    
    @BeforeEach
    void setUp() {
        appVersion = AppVersion.builder()
                .id(1L)
                .version("1.0.0")
                .platform(Platform.ANDROID)
                .releaseDate(LocalDateTime.now())
                .description("Test version")
                .updateType(UpdateType.MANDATORY)
                .isActive(true)
                .build();
        
        responseDto = new AppVersionResponseDto();
        responseDto.setVersion("1.0.0");
        responseDto.setPlatform("ANDROID");
        responseDto.setReleaseDate(LocalDateTime.now());
        responseDto.setChangelog("Test version");
        responseDto.setUpdateType(UpdateType.MANDATORY);
        
        createRequest = new CreateAppVersionRequest();
        createRequest.setVersion("2.0.0");
        createRequest.setPlatform(Platform.IOS);
        createRequest.setReleaseDate(LocalDateTime.now());
        createRequest.setChangelog("New features");
        createRequest.setUpdateType(UpdateType.OPTIONAL);
    }
    
    @Test
    void createVersion_Success() {
        when(mapper.toEntity(createRequest)).thenReturn(appVersion);
        when(repository.save(appVersion)).thenReturn(appVersion);
        when(mapper.toDto(appVersion)).thenReturn(responseDto);
        
        AppVersionResponseDto result = appVersionService.createVersion(createRequest);
        
        assertThat(result).isNotNull();
        assertThat(result.getVersion()).isEqualTo("1.0.0");
        verify(repository, times(1)).save(appVersion);
    }
    
    @Test
    void getAllVersions_Success() {
        AppVersion version2 = AppVersion.builder()
                .id(2L)
                .version("2.0.0")
                .platform(Platform.IOS)
                .build();
        
        AppVersionResponseDto dto2 = new AppVersionResponseDto();
        dto2.setVersion("2.0.0");
        dto2.setPlatform("IOS");
        
        when(repository.findAll()).thenReturn(Arrays.asList(appVersion, version2));
        when(mapper.toDto(appVersion)).thenReturn(responseDto);
        when(mapper.toDto(version2)).thenReturn(dto2);
        
        List<AppVersionResponseDto> result = appVersionService.getAllVersions();
        
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getVersion()).isEqualTo("1.0.0");
        assertThat(result.get(1).getVersion()).isEqualTo("2.0.0");
    }
    
    @Test
    void getVersionById_Success() {
        when(repository.findById(1L)).thenReturn(Optional.of(appVersion));
        when(mapper.toDto(appVersion)).thenReturn(responseDto);
        
        AppVersionResponseDto result = appVersionService.getVersionById(1L);
        
        assertThat(result).isNotNull();
        assertThat(result.getVersion()).isEqualTo("1.0.0");
    }
    
    @Test
    void getVersionById_NotFound() {
        when(repository.findById(999L)).thenReturn(Optional.empty());
        
        assertThatThrownBy(() -> appVersionService.getVersionById(999L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Version not found");
    }
    
    @Test
    void getLatestVersion_Success() {
        when(repository.findFirstByPlatformAndIsActiveTrueOrderByReleaseDateDesc(Platform.ANDROID))
                .thenReturn(Optional.of(appVersion));
        when(mapper.toDto(appVersion)).thenReturn(responseDto);
        
        AppVersionResponseDto result = appVersionService.getLatestVersion(Platform.ANDROID);
        
        assertThat(result).isNotNull();
        assertThat(result.getPlatform()).isEqualTo("ANDROID");
    }
    
    @Test
    void updateVersion_Success() {
        UpdateAppVersionRequest updateRequest = new UpdateAppVersionRequest();
        updateRequest.setChangelog("Updated changelog");
        updateRequest.setUpdateType(UpdateType.DEPRECATED);
        updateRequest.setIsActive(false);
        
        AppVersion updatedVersion = AppVersion.builder()
                .id(1L)
                .version("1.0.0")
                .platform(Platform.ANDROID)
                .releaseDate(LocalDateTime.now())
                .description("Updated changelog")
                .updateType(UpdateType.DEPRECATED)
                .isActive(false)
                .build();
        
        AppVersionResponseDto updatedDto = new AppVersionResponseDto();
        updatedDto.setVersion("1.0.0");
        updatedDto.setChangelog("Updated changelog");
        updatedDto.setUpdateType(UpdateType.DEPRECATED);
        
        when(repository.findById(1L)).thenReturn(Optional.of(appVersion));
        when(repository.save(any(AppVersion.class))).thenReturn(updatedVersion);
        when(mapper.toDto(updatedVersion)).thenReturn(updatedDto);
        
        AppVersionResponseDto result = appVersionService.updateVersion(1L, updateRequest);
        
        assertThat(result).isNotNull();
        assertThat(result.getChangelog()).isEqualTo("Updated changelog");
        assertThat(result.getUpdateType()).isEqualTo(UpdateType.DEPRECATED);
        verify(repository, times(1)).save(any(AppVersion.class));
    }
    
    @Test
    void deleteVersion_Success() {
        when(repository.findById(1L)).thenReturn(Optional.of(appVersion));
        when(repository.save(appVersion)).thenReturn(appVersion);
        
        appVersionService.deleteVersion(1L);
        
        assertThat(appVersion.isActive()).isFalse();
        verify(repository, times(1)).save(appVersion);
    }
}