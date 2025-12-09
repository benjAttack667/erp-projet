package fr.ece.project.models;

import java.time.LocalDate;

public class Project {
    private String id;
    private String name;
    private String description;
    private String managerId;
    private LocalDate startDate;
    private LocalDate endDate;

    public Project(String id, String name, String description, String managerId, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.managerId = managerId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Project(String name, String description, String managerId) {
        this.id = java.util.UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.managerId = managerId;
        this.startDate = LocalDate.now();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getManagerId() { return managerId; }
    public void setManagerId(String managerId) { this.managerId = managerId; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
}
