package com.kafein.jirabackend.repository;

import com.kafein.jirabackend.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository  extends JpaRepository<Activity,Integer> {
}
