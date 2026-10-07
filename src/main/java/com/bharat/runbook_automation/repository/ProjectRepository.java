package com.bharat.runbook_automation.repository;

import com.bharat.runbook_automation.model.Project;
import org.springframework.data.repository.ListCrudRepository;

public interface ProjectRepository extends ListCrudRepository<Project, Long> {

    Project findByKey(String key);

}
