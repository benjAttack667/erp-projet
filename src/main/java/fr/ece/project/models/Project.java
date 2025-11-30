package fr.ece.project.models;

public class Project {
    private int id;
    private String name;
    private String description;
    private int managerId;

    public Project() {}

    public Project(int id, String name, String description, int managerId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.managerId = managerId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getManagerId() { return managerId; }
    public void setManagerId(int managerId) { this.managerId = managerId; }
}

