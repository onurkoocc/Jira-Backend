package com.kafein.jirabackend.controller;

import com.kafein.jirabackend.model.*;
import com.kafein.jirabackend.payload.request.ActivityRequest;
import com.kafein.jirabackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/developer")
@PreAuthorize("hasRole('DEVELOPER')")
public class DeveloperController {
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

    @PostMapping("/solution")
    public void solution(@RequestBody ActivityRequest activityRequest){
        if(activityRequest != null && issueRepository.existsById(activityRequest.getIssueId())){

            Issue issue = issueRepository.getById(activityRequest.getIssueId());

            List<User> userList = userRepository.findAll();
            List<User> testersList = new ArrayList<User>();
            for(User user:userList){
                if(user.getRole().getName()== ERole.ROLE_TESTER){
                    testersList.add(user);
                }
            }
            Random rand = new Random();

            issue.setStatus(statusRepository.getByName(EStatus.Fixed));
            issue.setAssignedby(issue.getAssignedto());
            issue.setAssignedto(testersList.get(rand.nextInt(testersList.size())));

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

    @PostMapping("/noerror")
    public void noError(@RequestBody ActivityRequest activityRequest){
        if(activityRequest != null && issueRepository.existsById(activityRequest.getIssueId())){

            Issue issue = issueRepository.getById(activityRequest.getIssueId());

            issue.setStatus(statusRepository.getByName(EStatus.NoError));
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

    @PostMapping("/fixed")
    public void fixed(@RequestBody ActivityRequest activityRequest) {
        if (activityRequest != null && issueRepository.existsById(activityRequest.getIssueId())) {

            Issue issue = issueRepository.getById(activityRequest.getIssueId());

            issue.setStatus(statusRepository.getByName(EStatus.Fixed));
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




}
