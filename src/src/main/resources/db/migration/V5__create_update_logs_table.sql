CREATE TABLE update_logs (
    id BIGSERIAL PRIMARY KEY,
    user_id VARCHAR(255) NOT NULL,
    from_version VARCHAR(255) NOT NULL,
    to_version VARCHAR(255) NOT NULL,
    platform VARCHAR(50) NOT NULL,
    success BOOLEAN NOT NULL DEFAULT false,
    timestamp TIMESTAMP NOT NULL,
    error_message TEXT
);

CREATE INDEX idx_update_logs_user_id ON update_logs(user_id);
CREATE INDEX idx_update_logs_timestamp ON update_logs(timestamp);