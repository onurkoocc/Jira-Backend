package com.kafein.jirabackend.repository;

import com.kafein.jirabackend.model.EPriority;
import com.kafein.jirabackend.model.Priority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriorityRepository extends JpaRepository<Priority,Integer> {
    public Priority getByName(EPriority ePriority);
}
