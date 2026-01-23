package com.cpaums.dto;

import com.cpaums.model.Platform;
import com.cpaums.model.UpdateType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CreateAppVersionRequest {
    @NotBlank(message = "Version is required")
    private String version;
    
    @NotNull(message = "Platform is required")
    private Platform platform;
    
    @NotNull(message = "Release date is required")
    private LocalDateTime releaseDate;
    
    @NotBlank(message = "Changelog is required")
    private String changelog;
    
    @NotNull(message = "Update type is required")
    private UpdateType updateType;
}