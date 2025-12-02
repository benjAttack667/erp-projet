package fr.ece.project.services;

import fr.ece.project.dao.ProjectDAOImpl;
import fr.ece.project.models.Project;

import java.util.List;

public class ProjectService {
    private final ProjectDAOImpl projectDAO = new ProjectDAOImpl();

    public List<Project> getAllProjects() { return projectDAO.getAll(); }
    public Project findById(String id) { return projectDAO.findById(id); }
    public boolean createProject(Project p) { return projectDAO.save(p); }
}

