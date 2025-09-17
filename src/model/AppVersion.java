package com.example.app.model;

import java.time.LocalDateTime;

public class AppVersion {
    private String version;
    private Platform platform;
    private LocalDateTime releaseDate;
    private String changelog;
    private UpdateType updateType;
    private boolean isActive;

    public AppVersion() {}
    
    public AppVersion(String version, Platform platform, LocalDateTime releaseDate, String changelog, UpdateType updateType, boolean isActive) {
        this.version = version;
        this.platform = platform;
        this.releaseDate = releaseDate;
        this.changelog = changelog;
        this.updateType = updateType;
        this.isActive = isActive;
    }
}