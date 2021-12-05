package com.kafein.jirabackend.payload.response;

public class IssueResponse {
    private String name;
    private String status;
    private int creatorid;
    private String priority;
    private String description;
    private int assignedby;

    public IssueResponse() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public int getCreatorid() {
        return creatorid;
    }

    public void setCreatorid(int creatorid) {
        this.creatorid = creatorid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAssignedby() {
        return assignedby;
    }

    public void setAssignedby(int assignedby) {
        this.assignedby = assignedby;
    }

}
