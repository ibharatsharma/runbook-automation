package com.bharat.runbook_automation.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("project")
public record Project(
        @Id Long id,
        String key,
        String name,
        String description,
        String teamName,
        boolean isActive) {
}
