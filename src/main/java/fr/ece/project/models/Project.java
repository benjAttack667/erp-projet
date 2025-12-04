package fr.ece.project.models;

public class Project {
    private String id;
    private String name;
    private String description;
    private String managerId;

    public Project(String id, String name, String description, String managerId) {}

    public Project( String name, String description, String managerId) {
        this.id = java.util.UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.managerId = managerId;
    }


    public String getId() { return id; }


    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getManagerId() { return managerId; }
    public void setManagerId(String managerId) { this.managerId = managerId; }

    private void setId(String id) {
        this.id = id;
    }
    


}

