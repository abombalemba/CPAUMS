package com.cpaums.dto;

import com.cpaums.model.Platform;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UpdateLogDto {
    private String userId;
    private String fromVersion;
    private String toVersion;
    private Platform platform;
    private boolean success;
    private LocalDateTime timestamp;
}