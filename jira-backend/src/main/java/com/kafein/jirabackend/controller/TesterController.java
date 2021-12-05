package com.kafein.jirabackend.controller;

import com.kafein.jirabackend.model.Activity;
import com.kafein.jirabackend.model.EStatus;
import com.kafein.jirabackend.model.Issue;
import com.kafein.jirabackend.model.User;
import com.kafein.jirabackend.payload.request.ActivityRequest;
import com.kafein.jirabackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/tester")
@PreAuthorize("hasRole('TESTER')")
public class TesterController {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private PriorityRepository priorityRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/reopen")
    public void reopen(@RequestBody ActivityRequest activityRequest) {
        if (activityRequest != null && issueRepository.existsById(activityRequest.getIssueId())) {

            Issue issue = issueRepository.getById(activityRequest.getIssueId());

            issue.setStatus(statusRepository.getByName(EStatus.Reopened));
            User userTmp = issue.getAssignedby();
            issue.setAssignedby(issue.getAssignedto());
            issue.setAssignedto(userTmp);

            issueRepository.save(issue);

            Activity activity = new Activity(
                    activityRequest.getName(),
                    issueRepository.getById(activityRequest.getIssueId()),
                    Calendar.getInstance(),
                    activityRequest.getDescription(),
                    activityRequest.getStatusupdate(),
                    issue.getAssignedby()
            );
            activityRepository.save(activity);
        }
    }

    @PostMapping("/tested")
    public void tested(@RequestBody ActivityRequest activityRequest) {
        if (activityRequest != null && issueRepository.existsById(activityRequest.getIssueId())) {

            Issue issue = issueRepository.getById(activityRequest.getIssueId());

            issue.setStatus(statusRepository.getByName(EStatus.Tested));
            issue.setAssignedby(issue.getAssignedto());
            issue.setAssignedto(issue.getCreator());

            issueRepository.save(issue);

            Activity activity = new Activity(
                    activityRequest.getName(),
                    issueRepository.getById(activityRequest.getIssueId()),
                    Calendar.getInstance(),
                    activityRequest.getDescription(),
                    activityRequest.getStatusupdate(),
                    issue.getAssignedby()
            );
            activityRepository.save(activity);
        }
    }
}
