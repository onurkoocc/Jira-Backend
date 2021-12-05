package com.kafein.jirabackend.repository;

import com.kafein.jirabackend.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue,Integer> {
    @Override
    List<Issue> findAll();

}
