package fr.ece.project.models;

public class Task {
    private String id;
    private String title;
    private String status;
    private String projectId;
    private String assignedTo;

    public Task() {}

    public Task( String title, String status, String projectId, String assignedTo) {
        this.id = java.util.UUID.randomUUID().toString();;
        this.title = title;
        this.status = status;
        this.projectId = projectId;
        this.assignedTo = assignedTo;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getProjectId() { return projectId; }
    public void setProjectId(String projectId) { this.projectId = projectId; }

    public String getAssignedTo() { return assignedTo; }
    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }
}

