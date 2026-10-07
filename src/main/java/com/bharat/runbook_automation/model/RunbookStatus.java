package com.bharat.runbook_automation.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("runbook_status")
public record RunbookStatus(
        @Id Long id,
        String code,
        String label) {
}