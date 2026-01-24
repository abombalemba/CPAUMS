package com.cpaums.dto;

import com.cpaums.model.Platform;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateUserDeviceRequest {
    @NotBlank
    private String userId;
    
    @NotNull
    private Platform platform;
    
    @NotBlank
    private String currentVersion;
}
