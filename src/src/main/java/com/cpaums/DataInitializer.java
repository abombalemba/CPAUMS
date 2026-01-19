package com.cpaums;

import com.cpaums.model.AppVersion;
import com.cpaums.model.Platform;
import com.cpaums.model.UpdateType;
import com.cpaums.repository.AppVersionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    
    private final AppVersionRepository appVersionRepository;
    
    @Override
    public void run(String... args) {
        if (appVersionRepository.count() == 0) {
            AppVersion version = new AppVersion();
            version.setVersion("1.0.0");
            version.setPlatform(Platform.LINUX);
            version.setReleaseDate(LocalDateTime.now());
            version.setDescription("Первая версия приложения. Добавлена авторизация.");
            version.setUpdateType(UpdateType.OPTIONAL);
            version.setActive(true);
            
            appVersionRepository.save(version);
            System.out.println("✅ Тестовая версия сохранена в базу данных!");
        }
    }
}