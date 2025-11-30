package com.minierp.dao;

import com.minierp.models.Task;
import java.util.List;

public interface TaskDAO {
    List<Task> getByProject(int projectId);
    boolean save(Task task);
    boolean update(Task task);
    boolean delete(int taskId);
}
