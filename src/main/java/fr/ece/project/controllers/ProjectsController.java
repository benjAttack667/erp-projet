package fr.ece.project.controllers;

import fr.ece.project.models.Project;
import fr.ece.project.services.ProjectService;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class ProjectsController {

    @FXML private TableView<Project> projectTable;
    @FXML private TableColumn<Project, String> nameCol;
    @FXML private TableColumn<Project, String> startCol;
    @FXML private TableColumn<Project, String> endCol;
    @FXML private TableColumn<Project, String> managerCol;
    @FXML private TableColumn<Project, String> statusCol;
    @FXML private TableColumn<Project, String> lateCol;

    private final ProjectService projectService = new ProjectService();

    @FXML
    public void initialize() {
        // Lier les colonnes aux propriétés du modèle
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        managerCol.setCellValueFactory(new PropertyValueFactory<>("managerName"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));



        // Ajouter les projets au tableau qui les affiche,
        //normalement on affiche que les colonnes qui concernent l'user
        List<Project> projects = projectService.getAllProjects();
        projectTable.getItems().addAll(projects);
    }
}
