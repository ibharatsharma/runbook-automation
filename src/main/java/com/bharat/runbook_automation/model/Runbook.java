package com.bharat.runbook_automation.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import java.time.Instant;
import java.util.List;

@Table("runbook")
public record Runbook(
        @Id Long id,
        Long projectId,
        Long typeId,
        Long statusId,
        String title,
        String summary,
        String severity,
        String version,
        String createdBy,
        String updatedBy,
        Instant createdAt,
        Instant updatedAt,
        Instant lastReviewedAt,
        boolean isActive,

        @MappedCollection(idColumn = "runbook_id") List<RunbookStep> steps) {
}
