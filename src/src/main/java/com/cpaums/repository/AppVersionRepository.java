package com.cpaums.repository;

import com.cpaums.model.AppVersion;
import com.cpaums.model.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppVersionRepository extends JpaRepository<AppVersion, Long> {
    Optional<AppVersion> findFirstByPlatformAndIsActiveTrueOrderByReleaseDateDesc(Platform platform);
    Optional<AppVersion> findByVersionAndPlatform(String version, Platform platform);
}