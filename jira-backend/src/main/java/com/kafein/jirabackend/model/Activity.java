package com.kafein.jirabackend.model;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "activity")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id=0;

    private String name="";

    @ManyToOne
    @JoinColumn(name = "issueid")
    private Issue issue;

    private Calendar activityDate;

    private String description;

    private String statusupdate;

    @ManyToOne
    @JoinColumn(name = "updater")
    private User updater;

    public Activity() {
    }

    public Activity(String name, Issue issue, Calendar activityDate, String description, String statusupdate, User updater) {
        this.name = name;
        this.issue = issue;
        this.activityDate = activityDate;
        this.description = description;
        this.statusupdate = statusupdate;
        this.updater = updater;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public Calendar getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Calendar activityDate) {
        this.activityDate = activityDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatusupdate() {
        return statusupdate;
    }

    public void setStatusupdate(String statusupdate) {
        this.statusupdate = statusupdate;
    }

    public User getUpdater() {
        return updater;
    }

    public void setUpdater(User updater) {
        this.updater = updater;
    }
}
