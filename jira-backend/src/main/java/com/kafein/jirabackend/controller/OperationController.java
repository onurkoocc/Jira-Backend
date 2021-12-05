package com.kafein.jirabackend.controller;


import com.kafein.jirabackend.model.*;
import com.kafein.jirabackend.payload.request.ActivityRequest;
import com.kafein.jirabackend.payload.request.IssueRequest;
import com.kafein.jirabackend.repository.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/operation")
@PreAuthorize("hasRole('OPERATION')")
public class OperationController {
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

    @PostMapping("/createIssue")
    public void createIssue(@RequestBody IssueRequest issueRequest){
        if
        (userRepository.existsById(issueRequest.getCreatorid()) &&
                userRepository.existsById(issueRequest.getAssignedby()))
        {
            List<User> userList = userRepository.findAll();
            List<User> developersList = new ArrayList<User>();
            for(User user:userList){
                if(user.getRole().getName()==ERole.ROLE_DEVELOPER){
                    developersList.add(user);
                }
            }
            Random rand = new Random();
            Issue issue = new Issue(
                    issueRequest.getName(),
                    statusRepository.getByName(EStatus.Open),
                    issueRequest.getDescription(),
                    userRepository.getById(issueRequest.getCreatorid()),
                    Calendar.getInstance(),
                    developersList.get(rand.nextInt(developersList.size())),
                    userRepository.getById(issueRequest.getAssignedby()),
                    priorityRepository.getByName(EPriority.valueOf(issueRequest.getPriority()))
            );
            Issue issuetmp = issueRepository.save(issue);
            Activity activity = new Activity(
                    "Issue Created",
                    issuetmp,
                    Calendar.getInstance(),
                    issuetmp.getDescription(),
                    issuetmp.getStatus().toString(),
                    issuetmp.getAssignedby()
            );
            activityRepository.save(activity);
        }
    }

    @PostMapping("/fixedUpdate")
    public void fixedUpdate(@RequestBody ActivityRequest activityRequest){
        if(activityRequest != null && issueRepository.existsById(activityRequest.getIssueId())){
            Issue issue = issueRepository.getById(activityRequest.getIssueId());

            if(EStatus.Success.name().equals(activityRequest.getStatusupdate())){
                issue.setStatus(statusRepository.getByName(EStatus.Success));
                issue.setAssignedby(issue.getAssignedto());
                issue.setAssignedto(null);
            }
            else if(EStatus.ErrorGoesOn.name().equals(activityRequest.getStatusupdate())){
                issue.setStatus(statusRepository.getByName(EStatus.ErrorGoesOn));
                User userTmp = issue.getAssignedby();
                issue.setAssignedby(issue.getAssignedto());
                issue.setAssignedto(userTmp);
            }
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

    @PostMapping("/noErrorUpdate")
    public void noErrorUpdate(@RequestBody ActivityRequest activityRequest){
        if(activityRequest != null && issueRepository.existsById(activityRequest.getIssueId())){

            Issue issue = issueRepository.getById(activityRequest.getIssueId());

            if(EStatus.Closed.name().equals(activityRequest.getStatusupdate())){
                issue.setStatus(statusRepository.getByName(EStatus.Closed));
                issue.setAssignedby(issue.getAssignedto());
                issue.setAssignedto(null);
            }
            else if(EStatus.Reopened.name().equals(activityRequest.getStatusupdate())){
                issue.setStatus(statusRepository.getByName(EStatus.Reopened));
                User userTmp = issue.getAssignedby();
                issue.setAssignedby(issue.getAssignedto());
                issue.setAssignedto(userTmp);
            }
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
