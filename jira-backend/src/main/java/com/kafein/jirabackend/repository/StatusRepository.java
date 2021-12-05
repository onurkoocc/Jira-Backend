package com.kafein.jirabackend.repository;

import com.kafein.jirabackend.model.EStatus;
import com.kafein.jirabackend.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status,Integer> {
    Status getByName(EStatus eStatus);
}
