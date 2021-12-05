package com.kafein.jirabackend.payload.response;

public class ActivityResponse {
    private String name;
    private int issueId;
    private String description;
    private String statusupdate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
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
}
