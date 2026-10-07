package com.bharat.runbook_automation.repository;

import com.bharat.runbook_automation.model.RunbookType;
import org.springframework.data.repository.ListCrudRepository;

public interface RunbookTypeRepository extends ListCrudRepository<RunbookType, Long> {

    RunbookType findByCode(String code);
}
