package com.kafein.jirabackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "issue")
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id=0;

    private String name = "";

    @ManyToOne
    @JoinColumn(name = "statusid")
    private Status status;

    private String description = "";

    @ManyToOne
    @JoinColumn(name = "creatorid")
    private User creator;

    private Calendar lastUpdate;

    @ManyToOne
    @JoinColumn(name = "assignedto")
    private User assignedto;

    @ManyToOne
    @JoinColumn(name = "assignedby")
    private User assignedby;

    @ManyToOne
    @JoinColumn(name = "priorityid")
    private Priority priority;

    @JsonIgnore
    @OneToMany(mappedBy = "issue")
    private Set<Activity> activities = new HashSet<>();

    public Issue() {
    }

    public Issue(String name, Status status, String description, User creator, Calendar lastUpdate, User assignedto, User assignedby, Priority priority) {
        this.name = name;
        this.status = status;
        this.description = description;
        this.creator = creator;
        this.lastUpdate = lastUpdate;
        this.assignedto = assignedto;
        this.assignedby = assignedby;
        this.priority = priority;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Calendar getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Calendar lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public User getAssignedto() {
        return assignedto;
    }

    public void setAssignedto(User assignedto) {
        this.assignedto = assignedto;
    }

    public User getAssignedby() {
        return assignedby;
    }

    public void setAssignedby(User assignedby) {
        this.assignedby = assignedby;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
