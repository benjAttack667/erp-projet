package fr.ece.project.services;

import fr.ece.project.dao.TaskDAOImpl;
import fr.ece.project.models.Task;

import java.util.List;

public class TaskService {
    private final TaskDAOImpl taskDAO = new TaskDAOImpl();

    public List<Task> getByProject(int projectId) { return taskDAO.getByProject(projectId); }
    public boolean createTask(Task t) { return taskDAO.save(t); }
    public boolean updateTask(Task t) { return taskDAO.update(t); }
    public boolean deleteTask(int id) { return taskDAO.delete(id); }
}

