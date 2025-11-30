package fr.ece.project.models;

public class Comment {
    private int id;
    private int taskId;
    private int userId;
    private String content;

    public Comment() {}

    public Comment(int id, int taskId, int userId, String content) {
        this.id = id;
        this.taskId = taskId;
        this.userId = userId;
        this.content = content;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getTaskId() { return taskId; }
    public void setTaskId(int taskId) { this.taskId = taskId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}

