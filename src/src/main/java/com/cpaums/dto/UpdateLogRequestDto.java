package com.cpaums.dto;

import com.cpaums.model.Platform;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
public class UpdateLogRequestDto {
    @NotBlank
    private String userId;
    @NotBlank
    private String fromVersion;
    @NotBlank
    private String toVersion;
    @NotNull
    private Platform platform;
    private boolean success;
}