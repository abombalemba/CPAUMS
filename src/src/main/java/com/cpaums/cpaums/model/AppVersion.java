package com.cpaums.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "app_versions")
@Data
public class AppVersion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Version string cannot be empty")
    @Column(unique = true, nullable = false)
    private String version;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Platform platform;

    @NotNull
    private LocalDateTime releaseDate;

    @Column(columnDefinition = "TEXT")
    private String changelog;

    @NotNull
    @Enumerated(EnumType.STRING)
    private UpdateType updateType = UpdateType.OPTIONAL;

    private boolean isActive = true;
}