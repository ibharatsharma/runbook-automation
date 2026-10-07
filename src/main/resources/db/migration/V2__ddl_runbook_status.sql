CREATE TABLE runbook_status (
    id          BIGSERIAL PRIMARY KEY,
    code        VARCHAR(64) UNIQUE NOT NULL,
    label       VARCHAR(255) NOT NULL
);

INSERT INTO runbook_status (code, label) VALUES
('DRAFT', 'Draft'),
('APPROVED', 'Approved'),
('DEPRECATED', 'Deprecated');
