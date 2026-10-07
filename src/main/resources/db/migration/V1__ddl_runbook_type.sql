CREATE TABLE runbook_type (
    id          BIGSERIAL PRIMARY KEY,
    code        VARCHAR(64) UNIQUE NOT NULL,
    label       VARCHAR(255) NOT NULL
);

INSERT INTO runbook_type (code, label) VALUES
('INCIDENT_RESPONSE', 'Incident Response Runbooks'),
('DEPLOYMENT_RELEASE', 'Deployment & Release Runbooks'),
('MONITORING_ALERT_REMEDIATION', 'Monitoring & Alert Remediation Runbooks'),
('BACKUP_DR', 'Backup & Disaster Recovery Runbooks'),
('MAINTENANCE_ROUTINE_OPERATIONS', 'Maintenance & Routine Operations Runbooks'),
('PROVISIONING_INFRASTRUCTURE', 'Provisioning & Infrastructure Runbooks'),
('SECURITY_COMPLIANCE', 'Security & Compliance Runbooks'),
('DEVELOPER_ONBOARDING_LOCAL_SETUP', 'Developer Onboarding & Local Setup Runbooks');