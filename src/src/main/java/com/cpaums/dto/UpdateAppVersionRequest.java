package com.cpaums.dto;

import com.cpaums.model.UpdateType;
import lombok.Data;

@Data
public class UpdateAppVersionRequest {
    private String changelog;
    private UpdateType updateType;
    private Boolean isActive;
}