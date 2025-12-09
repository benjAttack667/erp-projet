package fr.ece.project.models;

import java.time.LocalDate;

public class Task {
    private String id;
    private String title;
    private String description;
    private String status;
    private LocalDate dueDate;
    private String projectId;
    private String assignedTo;

    public Task() {}

    public Task(String title, String description, String status, LocalDate dueDate, String projectId, String assignedTo) {
        this.id = java.util.UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.status = status;
        this.dueDate = dueDate;
        this.projectId = projectId;
        this.assignedTo = assignedTo;
    }

    // Getters et setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public String getProjectId() { return projectId; }
    public void setProjectId(String projectId) { this.projectId = projectId; }

    public String getAssignedTo() { return assignedTo; }
    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }
}
