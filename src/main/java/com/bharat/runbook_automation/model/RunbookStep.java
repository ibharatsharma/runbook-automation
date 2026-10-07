package com.bharat.runbook_automation.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
@Table("runbook_step")
public record RunbookStep(
        @Id Long id,
        Integer stepOrder,
        String title,
        String instruction,
        String expectedResult,
        String rollbackInstruction,
        Integer estimatedDurationMinutes,
        String automationRef) {
}
