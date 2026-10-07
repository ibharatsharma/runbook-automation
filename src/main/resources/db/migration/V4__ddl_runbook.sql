CREATE TABLE runbook (
    id                  BIGSERIAL PRIMARY KEY,
    project_id          BIGINT REFERENCES project(id),
    type_id             BIGINT NOT NULL REFERENCES runbook_type(id),
    status_id           BIGINT NOT NULL REFERENCES runbook_status(id),

    title               VARCHAR(255) NOT NULL,
    summary             TEXT,
    severity            VARCHAR(32),
    version             VARCHAR(32) NOT NULL DEFAULT '1',

    created_by          VARCHAR(128) NOT NULL,
    updated_by          VARCHAR(128),

    created_at          TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at          TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    last_reviewed_at    TIMESTAMPTZ,

    is_active           BOOLEAN NOT NULL DEFAULT TRUE
);
