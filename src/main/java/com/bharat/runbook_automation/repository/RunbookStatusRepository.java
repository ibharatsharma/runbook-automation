package com.bharat.runbook_automation.repository;

import com.bharat.runbook_automation.model.RunbookStatus;
import org.springframework.data.repository.ListCrudRepository;

public interface RunbookStatusRepository extends ListCrudRepository<RunbookStatus, Long> {

    RunbookStatus findByCode(String code);
}
