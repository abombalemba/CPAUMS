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
('1.0.0', 'WEB', NOW() - INTERVAL '65 days', 'Веб-версия приложения', 'OPTIONAL', true),
('1.5.0', 'WEB', NOW() - INTERVAL '35 days', 'PWA поддержка и оффлайн режим', 'OPTIONAL', true),
('2.0.0', 'WEB', NOW() - INTERVAL '10 days', 'Новый UI на React', 'MANDATORY', true),
('2.1.0', 'WEB', NOW() - INTERVAL '1 days', 'Ускорение загрузки на 40%', 'OPTIONAL', true);

INSERT INTO user_devices (user_id, platform, current_version, last_seen, device_model, os_version) VALUES
('user_001', 'ANDROID', '1.0.0', NOW() - INTERVAL '2 hours', 'Samsung Galaxy S23', 'Android 14'),
('user_002', 'ANDROID', '1.1.0', NOW() - INTERVAL '1 hour', 'Google Pixel 7', 'Android 14'),
('user_003', 'ANDROID', '2.0.0', NOW() - INTERVAL '30 minutes', 'Xiaomi Redmi Note 12', 'Android 13'),
('user_004', 'ANDROID', '2.2.0', NOW() - INTERVAL '15 minutes', 'OnePlus 11', 'Android 14'),
('user_005', 'ANDROID', '1.2.0', NOW() - INTERVAL '3 hours', 'Samsung Galaxy A54', 'Android 13'),
('user_006', 'ANDROID', '2.0.0', NOW() - INTERVAL '45 minutes', 'Google Pixel 6a', 'Android 13'),
('user_007', 'ANDROID', '2.1.0', NOW() - INTERVAL '20 minutes', 'Nothing Phone 2', 'Android 14'),
('user_008', 'ANDROID', '1.0.0', NOW() - INTERVAL '5 hours', 'Motorola Edge 40', 'Android 13'),
('user_009', 'ANDROID', '3.0.0', NOW() - INTERVAL '10 minutes', 'Samsung Galaxy S24', 'Android 15'),
('user_010', 'ANDROID', '2.3.0', NOW() - INTERVAL '25 minutes', 'Google Pixel 8', 'Android 14'),
('user_011', 'ANDROID', '1.1.0', NOW() - INTERVAL '4 hours', 'Xiaomi Poco X5', 'Android 13'),
('user_012', 'ANDROID', '2.2.0', NOW() - INTERVAL '50 minutes', 'OnePlus Nord 3', 'Android 14'),

('user_101', 'IOS', '1.0.0', NOW() - INTERVAL '4 hours', 'iPhone 13', 'iOS 17'),
('user_102', 'IOS', '1.5.0', NOW() - INTERVAL '2 hours', 'iPhone 14 Pro', 'iOS 17'),
('user_103', 'IOS', '2.0.0', NOW() - INTERVAL '1 hour', 'iPhone 15', 'iOS 17'),
('user_104', 'IOS', '2.1.0', NOW() - INTERVAL '40 minutes', 'iPhone 12', 'iOS 16'),
('user_105', 'IOS', '1.5.0', NOW() - INTERVAL '3 hours', 'iPhone 14', 'iOS 17'),
('user_106', 'IOS', '3.0.0', NOW() - INTERVAL '15 minutes', 'iPhone 15 Pro Max', 'iOS 17'),
('user_107', 'IOS', '2.2.0', NOW() - INTERVAL '55 minutes', 'iPhone 13 Pro', 'iOS 17'),
('user_108', 'IOS', '2.3.0', NOW() - INTERVAL '35 minutes', 'iPhone 14 Plus', 'iOS 17'),
('user_109', 'IOS', '1.0.0', NOW() - INTERVAL '6 hours', 'iPhone 11', 'iOS 16'),
('user_110', 'IOS', '2.0.0', NOW() - INTERVAL '70 minutes', 'iPhone SE 2022', 'iOS 16'),

('user_201', 'WINDOWS', '1.0.0', NOW() - INTERVAL '2 days', 'Custom Desktop', 'Windows 11'),
('user_202', 'WINDOWS', '1.2.0', NOW() - INTERVAL '1 day', 'Dell XPS 15', 'Windows 11'),
('user_203', 'WINDOWS', '2.0.0', NOW() - INTERVAL '12 hours', 'Lenovo ThinkPad', 'Windows 10'),
('user_204', 'WINDOWS', '2.1.0', NOW() - INTERVAL '6 hours', 'ASUS ROG', 'Windows 11'),
('user_205', 'WINDOWS', '2.2.0', NOW() - INTERVAL '3 hours', 'HP Spectre', 'Windows 11'),

('user_301', 'WEB', '1.0.0', NOW() - INTERVAL '5 days', 'Chrome Browser', 'Chrome 120'),
('user_302', 'WEB', '1.5.0', NOW() - INTERVAL '2 days', 'Firefox Browser', 'Firefox 121'),
('user_303', 'WEB', '2.0.0', NOW() - INTERVAL '1 day', 'Safari Browser', 'Safari 17'),
('user_304', 'WEB', '2.1.0', NOW() - INTERVAL '8 hours', 'Edge Browser', 'Edge 120'),
('user_305', 'WEB', '1.5.0', NOW() - INTERVAL '3 days', 'Chrome Mobile', 'Chrome 119');