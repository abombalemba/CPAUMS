package com.cpaums.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "app_versions", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"version", "platform"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppVersion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Version string cannot be empty")
    @NotNull
    @Column(nullable = false)
    private String version;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Platform platform;

    @NotNull
    private LocalDateTime releaseDate;

    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private UpdateType updateType = UpdateType.OPTIONAL;

    @Builder.Default
    private boolean isActive = true;
}