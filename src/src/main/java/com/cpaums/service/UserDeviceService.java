package com.cpaums.service;

import com.cpaums.dto.CreateUserDeviceRequest;
import com.cpaums.dto.UserDeviceResponseDto;
import com.cpaums.exception.ResourceNotFoundException;
import com.cpaums.mapper.UserDeviceMapper;
import com.cpaums.model.UserDevice;
import com.cpaums.repository.UserDeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDeviceService {
    
    private final UserDeviceRepository repository;
    private final UserDeviceMapper mapper;
    
    public UserDeviceResponseDto createDevice(CreateUserDeviceRequest request) {
        UserDevice device = mapper.toEntity(request);
        UserDevice saved = repository.save(device);
        return mapper.toDto(saved);
    }
    
    public List<UserDeviceResponseDto> getAllDevices() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
    
    public UserDeviceResponseDto getDeviceById(Long id) {
        UserDevice device = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Device not found with id: " + id
                ));
        return mapper.toDto(device);
    }
    
    public List<UserDeviceResponseDto> getDevicesByUserId(String userId) {
        return repository.findByUserId(userId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
    
    public UserDeviceResponseDto updateDeviceVersion(Long id, String newVersion) {
        UserDevice device = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Device not found with id: " + id
                ));
        device.setCurrentVersion(newVersion);
        device.setLastSeen(java.time.LocalDateTime.now());
        UserDevice saved = repository.save(device);
        return mapper.toDto(saved);
    }
    
    public void deleteDevice(Long id) {
        repository.deleteById(id);
    }
}
