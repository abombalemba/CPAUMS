package com.cpaums.dto;

import com.cpaums.model.Platform;
import lombok.Data;
import java.util.Map;

@Data
public class UpdateStatsDto {
    private Map<Platform, Integer> usersCount;
    private Double globalUpdateRate;
}