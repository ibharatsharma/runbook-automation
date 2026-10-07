CREATE TABLE project (
    id              BIGSERIAL PRIMARY KEY,
    key             VARCHAR(64) UNIQUE NOT NULL,
    name            VARCHAR(255) NOT NULL,
    description     TEXT,
    team_name       VARCHAR(255),
    is_active       BOOLEAN NOT NULL DEFAULT TRUE
);
