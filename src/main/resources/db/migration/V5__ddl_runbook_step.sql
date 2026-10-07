CREATE TABLE runbook_step (
    id                      BIGSERIAL PRIMARY KEY,
    runbook_id              BIGINT NOT NULL REFERENCES runbook(id) ON DELETE CASCADE,
    step_order              INT NOT NULL,

    title                   VARCHAR(255),
    instruction             TEXT NOT NULL,
    expected_result         TEXT,
    rollback_instruction    TEXT,
    estimated_duration_minutes INT,
    automation_ref          VARCHAR(255),

    CONSTRAINT uq_runbook_step_order UNIQUE (runbook_id, step_order)
);
