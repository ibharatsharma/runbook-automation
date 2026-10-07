package com.bharat.runbook_automation.web;


import com.bharat.runbook_automation.model.Runbook;
import com.bharat.runbook_automation.model.RunbookStep;
import com.bharat.runbook_automation.repository.RunbookRepository;
import com.bharat.runbook_automation.repository.RunbookTypeRepository;
import com.bharat.runbook_automation.repository.RunbookStatusRepository;
import com.bharat.runbook_automation.services.RunbookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/runbooks")
public class RunbookController {

    private final RunbookRepository runbookRepository;
    private final RunbookTypeRepository typeRepository;
    private final RunbookStatusRepository statusRepository;
    private final RunbookService runbookService;

    public RunbookController(
            RunbookRepository runbookRepository,
            RunbookTypeRepository typeRepository,
            RunbookStatusRepository statusRepository,
            RunbookService runbookService) {
        this.runbookRepository = runbookRepository;
        this.typeRepository = typeRepository;
        this.statusRepository = statusRepository;
        this.runbookService = runbookService;
    }

    // ------------------------------------------------------------
    // LIST PAGE
    // ------------------------------------------------------------
    @GetMapping
    public String list(Model model) {
        model.addAttribute("runbooks", runbookRepository.findAll());
        return "runbooks/list";
    }

    // ------------------------------------------------------------
    // DETAIL PAGE
    // ------------------------------------------------------------
    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("runbook", runbookService.getRunbook(id));
        model.addAttribute("types", typeRepository.findAll());
        model.addAttribute("statuses", statusRepository.findAll());
        return "runbooks/detail";
    }

    // ------------------------------------------------------------
    // ADD STEP (HTMX)
    // ------------------------------------------------------------
    @PostMapping("/{id}/steps")
    public String addStep(
            @PathVariable Long id,
            @RequestParam String title,
            @RequestParam String instruction,
            Model model) {
        RunbookStep step = new RunbookStep(
                null,
                null,
                title,
                instruction,
                null,
                null,
                null,
                null);

        Runbook updated = runbookService.addStep(id, step);
        model.addAttribute("runbook", updated);
        return "runbooks/fragments/steps :: steps";
    }

    // ------------------------------------------------------------
    // DELETE STEP (HTMX)
    // ------------------------------------------------------------
    @PostMapping("/{id}/steps/{order}/delete")
    public String deleteStep(
            @PathVariable Long id,
            @PathVariable int order,
            Model model) {
        Runbook updated = runbookService.deleteStep(id, order);
        model.addAttribute("runbook", updated);
        return "runbooks/fragments/steps :: steps";
    }

    // ------------------------------------------------------------
    // EDIT STEP FORM (HTMX)
    // ------------------------------------------------------------
    @GetMapping("/{id}/steps/{order}/edit")
    public String editStepForm(
            @PathVariable Long id,
            @PathVariable int order,
            Model model) {
        Runbook rb = runbookService.getRunbook(id);
        RunbookStep step = rb.steps().stream()
                .filter(s -> s.stepOrder() == order)
                .findFirst()
                .orElseThrow();

        model.addAttribute("step", step);
        model.addAttribute("runbookId", id);
        return "runbooks/fragments/edit-step :: editStepForm";
    }

    // ------------------------------------------------------------
    // UPDATE STEP (HTMX)
    // ------------------------------------------------------------
    @PostMapping("/{id}/steps/{order}/edit")
    public String updateStep(
            @PathVariable Long id,
            @PathVariable int order,
            @RequestParam String title,
            @RequestParam String instruction,
            Model model) {
        RunbookStep updatedStep = new RunbookStep(
                null,
                order,
                title,
                instruction,
                null,
                null,
                null,
                null);

        Runbook updated = runbookService.updateStep(id, order, updatedStep);
        model.addAttribute("runbook", updated);
        return "runbooks/fragments/steps :: steps";
    }
}
