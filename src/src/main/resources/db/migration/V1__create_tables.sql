CREATE TABLE app_versions (
    id BIGSERIAL PRIMARY KEY,
    version VARCHAR(255) NOT NULL,
    platform VARCHAR(50) NOT NULL,
    release_date TIMESTAMP NOT NULL,
    description TEXT,
    update_type VARCHAR(50) NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT true
);

CREATE TABLE user_devices (
    id BIGSERIAL PRIMARY KEY,
    user_id VARCHAR(255) NOT NULL,
    platform VARCHAR(50) NOT NULL,
    current_version VARCHAR(255) NOT NULL,
    last_seen TIMESTAMP
);