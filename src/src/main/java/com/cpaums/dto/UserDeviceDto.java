package com.cpaums.dto;

import com.cpaums.model.Platform;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserDeviceDto {
    private String userId;
    private Platform platform;
    private String currentVersion;
    private LocalDateTime lastSeen;
}