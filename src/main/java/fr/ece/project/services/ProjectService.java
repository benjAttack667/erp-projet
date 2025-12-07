package fr.ece.project.services;

import fr.ece.project.dao.ProjectDAOImpl;
import fr.ece.project.models.Project;

import java.util.List;

public class ProjectService {
    private final ProjectDAOImpl projectDAO = new ProjectDAOImpl();

    // Récupère tous les projets
    public List<Project> getAllProjects() {
        return projectDAO.getAll();
    }

    // Trouve un projet par ID
    public Project findById(String id) {
        return projectDAO.findById(id);
    }

    // Crée un projet
    public boolean createProject(Project project) {
        return projectDAO.save(project);
    }
}