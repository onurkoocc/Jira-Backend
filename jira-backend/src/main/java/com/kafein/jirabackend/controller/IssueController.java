package com.kafein.jirabackend.controller;

import com.kafein.jirabackend.model.Activity;
import com.kafein.jirabackend.model.EStatus;
import com.kafein.jirabackend.model.Issue;
import com.kafein.jirabackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/issues")
public class IssueController {
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

    @GetMapping("/issuesOnMe/{userid}")
    public List<Issue> issuesOnMe(@PathVariable int userid){
        List<Issue> issues = issueRepository.findAll();
        List<Issue> myIssues = new ArrayList<>();
        for(Issue issue : issues){
            if(issue.getAssignedto().getId() == userid &&
                    issue.getStatus() != statusRepository.getByName(EStatus.Success)){
                myIssues.add(issue);
            }
        }
        return myIssues;
    }

    @GetMapping("/closedIssuesWorked/{userid}")
    public List<Issue> closedIssuesOnMe(@PathVariable int userid){
        List<Issue> issues = issueRepository.findAll();
        List<Activity> activities = activityRepository.findAll();
        List<Issue> myIssues = new ArrayList<>();
        List<Integer> issueIds =  new ArrayList<>();
        for(Activity activity : activities){
            if(activity != null && activity.getUpdater().getId()==userid){
                if(!issueIds.contains(activity.getIssue().getId())){
                    issueIds.add(activity.getIssue().getId());
                    if(activity.getIssue().getStatus() ==statusRepository.getByName(EStatus.Closed) ||
                            activity.getIssue().getStatus() ==statusRepository.getByName(EStatus.Done) ||
                                    activity.getIssue().getStatus() ==statusRepository.getByName(EStatus.Cancelled)){
                        myIssues.add(activity.getIssue());
                    }
                }
            }
        }

        return myIssues;
    }


}
