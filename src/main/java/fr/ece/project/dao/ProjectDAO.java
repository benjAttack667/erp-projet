package fr.ece.project.dao;

import fr.ece.project.models.Project;
import java.util.List;

public interface ProjectDAO {
    List<Project> getAll();
    Project findById(int id);
    boolean save(Project p);
    boolean update(Project p);
    boolean delete(int id);
}
