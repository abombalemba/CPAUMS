package com.cpaums.dto;

import com.cpaums.model.UpdateType;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AppVersionResponseDto {
    private String version;
    private String platform;
    private LocalDateTime releaseDate;
    private String changelog;
    private UpdateType updateType;
}