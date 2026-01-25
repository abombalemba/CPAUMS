package com.cpaums.service;

import com.cpaums.dto.CreateUserDeviceRequest;
import com.cpaums.dto.UserDeviceResponseDto;
import com.cpaums.exception.ResourceNotFoundException;
import com.cpaums.mapper.UserDeviceMapper;
import com.cpaums.model.Platform;
import com.cpaums.model.UserDevice;
import com.cpaums.repository.UserDeviceRepository;
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
class UserDeviceServiceTest {
    
    @Mock
    private UserDeviceRepository repository;
    
    @Mock
    private UserDeviceMapper mapper;
    
    @InjectMocks
    private UserDeviceService userDeviceService;
    
    private UserDevice userDevice;
    private UserDeviceResponseDto responseDto;
    private CreateUserDeviceRequest createRequest;
    
    @BeforeEach
    void setUp() {
        userDevice = new UserDevice();
        userDevice.setId(1L);
        userDevice.setUserId("user123");
        userDevice.setPlatform(Platform.ANDROID);
        userDevice.setCurrentVersion("1.0.0");
        userDevice.setLastSeen(LocalDateTime.now());
        
        responseDto = new UserDeviceResponseDto();
        responseDto.setId(1L);
        responseDto.setUserId("user123");
        responseDto.setPlatform(Platform.ANDROID);
        responseDto.setCurrentVersion("1.0.0");
        responseDto.setLastSeen(LocalDateTime.now());
        
        createRequest = new CreateUserDeviceRequest();
        createRequest.setUserId("user456");
        createRequest.setPlatform(Platform.IOS);
        createRequest.setCurrentVersion("2.0.0");
    }
    
    @Test
    void createDevice_Success() {
        UserDevice newDevice = new UserDevice();
        newDevice.setUserId("user456");
        newDevice.setPlatform(Platform.IOS);
        newDevice.setCurrentVersion("2.0.0");
        
        UserDeviceResponseDto newDto = new UserDeviceResponseDto();
        newDto.setUserId("user456");
        newDto.setPlatform(Platform.IOS);
        newDto.setCurrentVersion("2.0.0");
        
        when(mapper.toEntity(createRequest)).thenReturn(newDevice);
        when(repository.save(newDevice)).thenReturn(newDevice);
        when(mapper.toDto(newDevice)).thenReturn(newDto);
        
        UserDeviceResponseDto result = userDeviceService.createDevice(createRequest);
        
        assertThat(result).isNotNull();
        assertThat(result.getUserId()).isEqualTo("user456");
        verify(repository, times(1)).save(newDevice);
    }
    
    @Test
    void getAllDevices_Success() {
        UserDevice device2 = new UserDevice();
        device2.setId(2L);
        device2.setUserId("user456");
        
        UserDeviceResponseDto dto2 = new UserDeviceResponseDto();
        dto2.setId(2L);
        dto2.setUserId("user456");
        
        when(repository.findAll()).thenReturn(Arrays.asList(userDevice, device2));
        when(mapper.toDto(userDevice)).thenReturn(responseDto);
        when(mapper.toDto(device2)).thenReturn(dto2);
        
        List<UserDeviceResponseDto> result = userDeviceService.getAllDevices();
        
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getUserId()).isEqualTo("user123");
        assertThat(result.get(1).getUserId()).isEqualTo("user456");
    }
    
    @Test
    void getDeviceById_Success() {
        when(repository.findById(1L)).thenReturn(Optional.of(userDevice));
        when(mapper.toDto(userDevice)).thenReturn(responseDto);
        
        UserDeviceResponseDto result = userDeviceService.getDeviceById(1L);
        
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getUserId()).isEqualTo("user123");
    }
    
    @Test
    void getDeviceById_NotFound() {
        when(repository.findById(999L)).thenReturn(Optional.empty());
        
        assertThatThrownBy(() -> userDeviceService.getDeviceById(999L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Device not found");
    }
    
    @Test
    void getDevicesByUserId_Success() {
        UserDevice device2 = new UserDevice();
        device2.setId(2L);
        device2.setUserId("user123");
        
        UserDeviceResponseDto dto2 = new UserDeviceResponseDto();
        dto2.setId(2L);
        dto2.setUserId("user123");
        
        when(repository.findByUserId("user123")).thenReturn(Arrays.asList(userDevice, device2));
        when(mapper.toDto(userDevice)).thenReturn(responseDto);
        when(mapper.toDto(device2)).thenReturn(dto2);
        
        List<UserDeviceResponseDto> result = userDeviceService.getDevicesByUserId("user123");
        
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getUserId()).isEqualTo("user123");
        assertThat(result.get(1).getUserId()).isEqualTo("user123");
    }
    
    @Test
    void updateDeviceVersion_Success() {
        UserDevice updatedDevice = new UserDevice();
        updatedDevice.setId(1L);
        updatedDevice.setUserId("user123");
        updatedDevice.setCurrentVersion("2.0.0");
        
        UserDeviceResponseDto updatedDto = new UserDeviceResponseDto();
        updatedDto.setId(1L);
        updatedDto.setCurrentVersion("2.0.0");
        
        when(repository.findById(1L)).thenReturn(Optional.of(userDevice));
        when(repository.save(any(UserDevice.class))).thenReturn(updatedDevice);
        when(mapper.toDto(updatedDevice)).thenReturn(updatedDto);
        
        UserDeviceResponseDto result = userDeviceService.updateDeviceVersion(1L, "2.0.0");
        
        assertThat(result).isNotNull();
        assertThat(result.getCurrentVersion()).isEqualTo("2.0.0");
        verify(repository, times(1)).save(any(UserDevice.class));
    }
    
    @Test
    void deleteDevice_Success() {
        doNothing().when(repository).deleteById(1L);
        
        userDeviceService.deleteDevice(1L);
        
        verify(repository, times(1)).deleteById(1L);
    }
}