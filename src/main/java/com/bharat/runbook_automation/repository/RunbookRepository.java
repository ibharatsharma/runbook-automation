package com.bharat.runbook_automation.repository;

import com.bharat.runbook_automation.model.Runbook;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface RunbookRepository extends ListCrudRepository<Runbook, Long> {

    List<Runbook> findByProjectId(Long projectId);

    List<Runbook> findByTypeId(Long typeId);

    List<Runbook> findByStatusId(Long statusId);

    List<Runbook> findByIsActiveTrue();
}
