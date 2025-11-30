package com.minierp.dao;

import com.minierp.models.Project;
import java.util.List;

public interface ProjectDAO {
    Project findById(int id);
    List<Project> getAll();
    boolean save(Project project);
    boolean update(Project project);
    boolean delete(int id);
}
