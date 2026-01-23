package com.cpaums.dto;

import com.cpaums.model.UpdateType;
import lombok.Data;

@Data
public class UpdateCheckResponseDto {
    private boolean updateAvailable;
    private String latestVersion;
    private UpdateType updateType;
    private String message;
}