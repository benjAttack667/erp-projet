package fr.ece.project.models;

public class Task {
    private int id;
    private String title;
    private String status;
    private int projectId;
    private int assignedTo;

    public Task() {}

    public Task(int id, String title, String status, int projectId, int assignedTo) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.projectId = projectId;
        this.assignedTo = assignedTo;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getProjectId() { return projectId; }
    public void setProjectId(int projectId) { this.projectId = projectId; }

    public int getAssignedTo() { return assignedTo; }
    public void setAssignedTo(int assignedTo) { this.assignedTo = assignedTo; }
}

