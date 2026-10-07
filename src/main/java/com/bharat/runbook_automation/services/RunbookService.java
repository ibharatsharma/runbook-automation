package com.bharat.runbook_automation.services;


import com.bharat.runbook_automation.model.Runbook;
import com.bharat.runbook_automation.model.RunbookStep;
import com.bharat.runbook_automation.repository.RunbookRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class RunbookService {

    private final RunbookRepository runbookRepository;

    public RunbookService(RunbookRepository runbookRepository) {
        this.runbookRepository = runbookRepository;
    }

    // ------------------------------------------------------------
    // CREATE
    // ------------------------------------------------------------
    public Runbook createRunbook(Runbook runbook) {
        return runbookRepository.save(runbook);
    }

    // ------------------------------------------------------------
    // READ
    // ------------------------------------------------------------
    public Runbook getRunbook(Long id) {
        return runbookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Runbook not found: " + id));
    }

    // ------------------------------------------------------------
    // UPDATE ROOT FIELDS (aggregate-safe)
    // ------------------------------------------------------------
    public Runbook updateRunbookMetadata(Long id, String title, String summary, Long typeId, Long statusId) {
        Runbook rb = getRunbook(id);

        Runbook updated = new Runbook(
                rb.id(),
                rb.projectId(),
                typeId,
                statusId,
                title,
                summary,
                rb.severity(),
                rb.version(),
                rb.createdBy(),
                rb.updatedBy(),
                rb.createdAt(),
                Instant.now(),
                rb.lastReviewedAt(),
                rb.isActive(),
                rb.steps());

        return runbookRepository.save(updated);
    }

    // ------------------------------------------------------------
    // ADD STEP
    // ------------------------------------------------------------
    public Runbook addStep(Long runbookId, RunbookStep newStep) {
        Runbook rb = getRunbook(runbookId);

        List<RunbookStep> steps = new ArrayList<>(rb.steps());

        int nextOrder = steps.stream()
                .map(RunbookStep::stepOrder)
                .max(Comparator.naturalOrder())
                .orElse(0) + 1;

        RunbookStep stepWithOrder = new RunbookStep(
                null,
                nextOrder,
                newStep.title(),
                newStep.instruction(),
                newStep.expectedResult(),
                newStep.rollbackInstruction(),
                newStep.estimatedDurationMinutes(),
                newStep.automationRef());

        steps.add(stepWithOrder);

        Runbook updated = rb.withSteps(steps);
        return runbookRepository.save(updated);
    }

    // ------------------------------------------------------------
    // UPDATE STEP
    // ------------------------------------------------------------
    public Runbook updateStep(Long runbookId, int stepOrder, RunbookStep updatedStep) {
        Runbook rb = getRunbook(runbookId);

        List<RunbookStep> steps = new ArrayList<>(rb.steps());

        List<RunbookStep> newSteps = steps.stream()
                .map(step -> {
                    if (step.stepOrder() == stepOrder) {
                        return new RunbookStep(
                                step.id(),
                                stepOrder,
                                updatedStep.title(),
                                updatedStep.instruction(),
                                updatedStep.expectedResult(),
                                updatedStep.rollbackInstruction(),
                                updatedStep.estimatedDurationMinutes(),
                                updatedStep.automationRef());
                    }
                    return step;
                })
                .toList();

        Runbook updated = rb.withSteps(newSteps);
        return runbookRepository.save(updated);
    }

    // ------------------------------------------------------------
    // DELETE STEP
    // ------------------------------------------------------------
    public Runbook deleteStep(Long runbookId, int stepOrder) {
        Runbook rb = getRunbook(runbookId);

        List<RunbookStep> newSteps = rb.steps().stream()
                .filter(step -> step.stepOrder() != stepOrder)
                .toList();

        Runbook updated = rb.withSteps(newSteps);
        return runbookRepository.save(updated);
    }

    // ------------------------------------------------------------
    // REORDER STEPS
    // ------------------------------------------------------------
    public Runbook reorderSteps(Long runbookId, List<Integer> newOrder) {
        Runbook rb = getRunbook(runbookId);

        if (newOrder.size() != rb.steps().size()) {
            throw new IllegalArgumentException("Order list size mismatch");
        }

        List<RunbookStep> reordered = new ArrayList<>();

        for (int i = 0; i < newOrder.size(); i++) {
            int targetOrder = newOrder.get(i);

            RunbookStep step = rb.steps().stream()
                    .filter(s -> s.stepOrder() == targetOrder)
                    .findFirst()
                    .orElseThrow();

            reordered.add(new RunbookStep(
                    step.id(),
                    i + 1,
                    step.title(),
                    step.instruction(),
                    step.expectedResult(),
                    step.rollbackInstruction(),
                    step.estimatedDurationMinutes(),
                    step.automationRef()));
        }

        Runbook updated = rb.withSteps(reordered);
        return runbookRepository.save(updated);
    }
}
