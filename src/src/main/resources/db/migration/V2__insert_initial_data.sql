INSERT INTO app_versions (version, platform, release_date, description, update_type, is_active) VALUES
('1.0.0', 'ANDROID', NOW() - INTERVAL '90 days', 'Первая версия для Android', 'DEPRECATED', false),
('1.1.0', 'ANDROID', NOW() - INTERVAL '60 days', 'Исправлены критические баги', 'OPTIONAL', true),
('1.2.0', 'ANDROID', NOW() - INTERVAL '45 days', 'Новые иконки и анимации', 'OPTIONAL', true),
('2.0.0', 'ANDROID', NOW() - INTERVAL '30 days', 'Полный редизайн интерфейса', 'MANDATORY', true),
('2.1.0', 'ANDROID', NOW() - INTERVAL '15 days', 'Оптимизация потребления памяти', 'OPTIONAL', true),
('2.2.0', 'ANDROID', NOW() - INTERVAL '5 days', 'Тёмная тема и новые виджеты', 'MANDATORY', true),
('2.3.0', 'ANDROID', NOW() - INTERVAL '2 days', 'Исправление утечек памяти', 'OPTIONAL', true),
('3.0.0', 'ANDROID', NOW() - INTERVAL '1 days', 'Новая архитектура приложения', 'MANDATORY', true);

INSERT INTO app_versions (version, platform, release_date, description, update_type, is_active) VALUES
('1.0.0', 'IOS', NOW() - INTERVAL '85 days', 'Первая версия для iOS', 'DEPRECATED', false),
('1.5.0', 'IOS', NOW() - INTERVAL '55 days', 'Поддержка Dynamic Island', 'OPTIONAL', true),
('2.0.0', 'IOS', NOW() - INTERVAL '28 days', 'Интеграция с Apple Health', 'MANDATORY', true),
('2.1.0', 'IOS', NOW() - INTERVAL '12 days', 'Исправление crash на iOS 17', 'OPTIONAL', true),
('2.2.0', 'IOS', NOW() - INTERVAL '6 days', 'Поддержка iPad multitasking', 'OPTIONAL', true),
('2.3.0', 'IOS', NOW() - INTERVAL '3 days', 'Улучшение автономности', 'OPTIONAL', true),
('3.0.0', 'IOS', NOW() - INTERVAL '1 days', 'Новая система уведомлений', 'MANDATORY', true);

INSERT INTO app_versions (version, platform, release_date, description, update_type, is_active) VALUES
('1.0.0', 'WINDOWS', NOW() - INTERVAL '70 days', 'Десктопная версия', 'OPTIONAL', true),
('1.2.0', 'WINDOWS', NOW() - INTERVAL '40 days', 'Панель уведомлений Windows', 'OPTIONAL', true),
('2.0.0', 'WINDOWS', NOW() - INTERVAL '20 days', 'Поддержка Windows 11', 'MANDATORY', true),
('2.1.0', 'WINDOWS', NOW() - INTERVAL '8 days', 'Интеграция с Microsoft Store', 'OPTIONAL', true),
('2.2.0', 'WINDOWS', NOW() - INTERVAL '2 days', 'Исправление драйверов звука', 'OPTIONAL', true);

INSERT INTO app_versions (version, platform, release_date, description, update_type, is_active) VALUES
('1.0.0', 'LINUX', NOW() - INTERVAL '75 days', 'Первая версия для Linux', 'OPTIONAL', true),
('1.1.0', 'LINUX', NOW() - INTERVAL '50 days', 'Поддержка Wayland', 'OPTIONAL', true),
('1.2.0', 'LINUX', NOW() - INTERVAL '30 days', 'Интеграция с systemd', 'MANDATORY', true),
('2.0.0', 'LINUX', NOW() - INTERVAL '15 days', 'Поддержка Snap и Flatpak', 'OPTIONAL', true),
('2.1.0', 'LINUX', NOW() - INTERVAL '5 days', 'Исправление проблем с AppImage', 'OPTIONAL', true);

INSERT INTO app_versions (version, platform, release_date, description, update_type, is_active) VALUES
('1.0.0', 'WEB', NOW() - INTERVAL '65 days', 'Веб-версия приложения', 'OPTIONAL', true),
('1.5.0', 'WEB', NOW() - INTERVAL '35 days', 'PWA поддержка и оффлайн режим', 'OPTIONAL', true),
('2.0.0', 'WEB', NOW() - INTERVAL '10 days', 'Новый UI на React', 'MANDATORY', true),
('2.1.0', 'WEB', NOW() - INTERVAL '1 days', 'Ускорение загрузки на 40%', 'OPTIONAL', true);

INSERT INTO user_devices (user_id, platform, current_version, last_seen) VALUES
('user_001', 'ANDROID', '1.0.0', NOW() - INTERVAL '2 hours'),
('user_002', 'ANDROID', '1.1.0', NOW() - INTERVAL '1 hour'),
('user_003', 'ANDROID', '2.0.0', NOW() - INTERVAL '30 minutes'),
('user_004', 'ANDROID', '2.2.0', NOW() - INTERVAL '15 minutes'),
('user_005', 'ANDROID', '1.2.0', NOW() - INTERVAL '3 hours'),
('user_006', 'ANDROID', '2.0.0', NOW() - INTERVAL '45 minutes'),
('user_007', 'ANDROID', '2.1.0', NOW() - INTERVAL '20 minutes'),
('user_008', 'ANDROID', '1.0.0', NOW() - INTERVAL '5 hours'),
('user_009', 'ANDROID', '3.0.0', NOW() - INTERVAL '10 minutes'),
('user_010', 'ANDROID', '2.3.0', NOW() - INTERVAL '25 minutes'),
('user_011', 'ANDROID', '1.1.0', NOW() - INTERVAL '4 hours'),
('user_012', 'ANDROID', '2.2.0', NOW() - INTERVAL '50 minutes'),

('user_101', 'IOS', '1.0.0', NOW() - INTERVAL '4 hours'),
('user_102', 'IOS', '1.5.0', NOW() - INTERVAL '2 hours'),
('user_103', 'IOS', '2.0.0', NOW() - INTERVAL '1 hour'),
('user_104', 'IOS', '2.1.0', NOW() - INTERVAL '40 minutes'),
('user_105', 'IOS', '1.5.0', NOW() - INTERVAL '3 hours'),
('user_106', 'IOS', '3.0.0', NOW() - INTERVAL '15 minutes'),
('user_107', 'IOS', '2.2.0', NOW() - INTERVAL '55 minutes'),
('user_108', 'IOS', '2.3.0', NOW() - INTERVAL '35 minutes'),
('user_109', 'IOS', '1.0.0', NOW() - INTERVAL '6 hours'),
('user_110', 'IOS', '2.0.0', NOW() - INTERVAL '70 minutes'),

('user_201', 'WINDOWS', '1.0.0', NOW() - INTERVAL '2 days'),
('user_202', 'WINDOWS', '1.2.0', NOW() - INTERVAL '1 day'),
('user_203', 'WINDOWS', '2.0.0', NOW() - INTERVAL '12 hours'),
('user_204', 'WINDOWS', '2.1.0', NOW() - INTERVAL '6 hours'),
('user_205', 'WINDOWS', '2.2.0', NOW() - INTERVAL '3 hours'),

('user_206', 'LINUX', '1.1.0', NOW() - INTERVAL '1 day'),
('user_207', 'LINUX', '1.2.0', NOW() - INTERVAL '12 hours'),
('user_208', 'LINUX', '2.0.0', NOW() - INTERVAL '6 hours'),
('user_209', 'LINUX', '1.0.0', NOW() - INTERVAL '3 days'),
('user_210', 'LINUX', '2.1.0', NOW() - INTERVAL '2 hours'),

('user_301', 'WEB', '1.0.0', NOW() - INTERVAL '5 days'),
('user_302', 'WEB', '1.5.0', NOW() - INTERVAL '2 days'),
('user_303', 'WEB', '2.0.0', NOW() - INTERVAL '1 day'),
('user_304', 'WEB', '2.1.0', NOW() - INTERVAL '8 hours'),
('user_305', 'WEB', '1.5.0', NOW() - INTERVAL '3 days');