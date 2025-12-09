package fr.ece.project.dao;

import fr.ece.project.models.Task;
import java.util.List;

public interface TaskDAO {


    List<Task> getByUser(String userId);

    List<Task> getByProject(String projectId);
    boolean save(Task task);
    boolean update(Task task);
    boolean delete(String taskId);

    List<Task> getAllTasks();


}

