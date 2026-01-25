INSERT INTO roles (name) VALUES ('ADMIN'), ('DEVELOPER'), ('USER');

INSERT INTO users (username, password) VALUES
('admin', '$2b$12$.40dOicMCZpIsnFsIn8Zke/NDM5yKhC3/18PqYRpJTf4Cg8nzWEgu'),
('dev', '$2b$12$p5OP/Hg58H1HDfi9sma0feovG/.M/7/cEI9uCdvPJxOSJLDmQHwNu'),
('user1', '$2b$12$3le.Wgq.K6YaaMjm77q5qOQI4uIozuHkq8TWmVtXAVwRfXC9urNwe'),
('user2', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lOBslKX4qC8pQm'),
('user3', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lOBslKX4qC8pQm'),
('user4', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lOBslKX4qC8pQm'),
('user5', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lOBslKX4qC8pQm'),
('user6', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lOBslKX4qC8pQm'),
('user7', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lOBslKX4qC8pQm'),
('user8', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lOBslKX4qC8pQm');

INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);

INSERT INTO user_roles (user_id, role_id) VALUES (2, 2);

INSERT INTO user_roles (user_id, role_id) VALUES (3, 3), (4, 3), (5, 3), (6, 3), (7, 3), (8, 3), (9, 3), (10, 3);